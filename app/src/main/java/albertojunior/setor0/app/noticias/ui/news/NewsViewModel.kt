package albertojunior.setor0.app.noticias.ui.news

import albertojunior.setor0.app.noticias.exception.NewsException
import albertojunior.setor0.app.noticias.extension.combine
import albertojunior.setor0.app.noticias.model.District
import albertojunior.setor0.app.noticias.model.News
import albertojunior.setor0.app.noticias.model.NewsDTO
import albertojunior.setor0.app.noticias.model.prompter.PrefabsPrompt
import albertojunior.setor0.app.noticias.model.prompter.PromptDistrictNews
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class NewsViewModel @Inject constructor(
    @Named("Default") private val generativeModel: GenerativeModel,
    private val repository: NewsRepository,
) : ViewModel() {
    private val channels = District.districts

    private val _currentChannel = MutableLiveData(0)
    val channel = _currentChannel.map { "Canal $it: ${channels[it].name}" }

    private val _titleText = MutableLiveData("Sem notícias.")
    val titleText: LiveData<String> = _titleText
    private val _newsText = MutableLiveData("")
    val newsText: LiveData<String> = _newsText

    private val _loading = MutableLiveData(false)
    val loadingVisibility = _loading.map { if (it) View.VISIBLE else View.GONE }
    val noticeVisibility = _loading.map { if (!it) View.VISIBLE else View.GONE }
    val channelButtonsEnabled = _loading.map { !it }

    val copyButtonEnabled = combine(_loading, _titleText, _newsText) { isLoad, title, news ->
        val safeA = isLoad ?: false
        val safeB = title ?: "Sem notícias."
        val safeC = news.orEmpty()
        !safeA && safeB.isNotEmpty() && safeB != "Sem notícias." && safeC.isNotEmpty()
    }

    val refreshButtonEnabled = _loading.map { !it }

    fun beforeChannel() {
        var channel = _currentChannel.value?.minus(1) ?: 0
        if (channel <= -1)
            channel = channels.lastIndex

        _currentChannel.value = channel

        getNews(channels[channel])
    }

    fun nextChannel() {
        var channel = _currentChannel.value?.plus(1) ?: 0
        if (channel > channels.lastIndex)
            channel = 0

        _currentChannel.value = channel

        getNews(channels[channel])
    }

    private fun getNews(district: District) {
        _loading.value = true
        viewModelScope.launch {
            val oldNews = repository.getNews(district.name)
            val prompt = PromptDistrictNews(district, oldNews).mount()

            runCatching {
                println(prompt)
                generativeModel.generateContent(prompt)
            }.onSuccess {
                operateNotice(it, district)
            }.onFailure {
                println(it)
                _titleText.value = "Comunicação comprometida."
                _newsText.value = "Está transmissão foi interrompida. Voltamos em alguns instantes."
            }

            _loading.value = false
        }
    }

    private fun operateNotice(
        it: GenerateContentResponse,
        district: District
    ) {
        runCatching {
            it.text
                ?.let { news ->
                    val cleanNews = cleanNotice(news)
                    convertToNewsDTO(cleanNews)
                }
                ?: throw NewsException("News is null")
        }.onSuccess { auxNews ->
            showNews(auxNews.title, auxNews.news)
            fetchInformationOnNews(district, auxNews)
        }.onFailure {
            showNews("Comunicação comprometida.", "Nenhuma notícia no momento.")
        }
    }

    private fun cleanNotice(notice: String): String {
        val cleanNotice =
            notice
                .replace("android", "androide")
                .replace("Android", "Androide")
                .replace("*", "")
        return cleanNotice
    }

    private fun showNews(title: String, news: String) {
        _titleText.value = title
        _newsText.value = news
    }

    private fun convertToNewsDTO(notice: String): NewsDTO {
        runCatching {
            Gson().fromJson(notice, NewsDTO::class.java)
        }.onSuccess {
            return it
        }.onFailure {
            val firstMark = notice.indexOf("\n\n")
            val title = notice.substring(0, firstMark)
            val news = notice.substring(firstMark, notice.length)

            if (title.isNotBlank() && news.isNotBlank()) {
                return NewsDTO(title, news)
            }
        }

        throw NewsException("Conversion failure")
    }

    private fun fetchInformationOnNews(district: District, news: NewsDTO) {
        viewModelScope.launch {
            runCatching {
                val prompt = PrefabsPrompt.PREFAB_GET_CORE_INFO + "Título: ${news.title}\nNotícia: ${news.news}"
                return@runCatching generativeModel.generateContent(prompt)
            }.onSuccess {
                it.text?.let { text ->
                    val cleanedText = text.replace("*", "")
                    val element = News(district, news.title, news.news, cleanedText)
                    println("Informações: \n$cleanedText")
                    repository.addNews(element)
                }
            }.onFailure {
                println(it)
            }
        }
    }

    fun refreshNews() {
        val channel = _currentChannel.value ?: return
        getNews(channels[channel])
    }

    fun copyMessage(context: Context) {
        val textToCopy = _newsText.value.orEmpty()

        if (textToCopy.isEmpty())
            return

        // Obtenha o gerenciador de área de transferência
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        // Cria um ClipData para guardar o texto
        val clipData = ClipData.newPlainText("Texto copiado", textToCopy)

        // Define o ClipData na área de transferência
        clipboardManager.setPrimaryClip(clipData)
    }
}
