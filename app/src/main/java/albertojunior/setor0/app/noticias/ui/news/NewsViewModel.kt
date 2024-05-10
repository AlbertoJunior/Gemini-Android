package albertojunior.setor0.app.noticias.ui.news

import albertojunior.setor0.app.noticias.R
import albertojunior.setor0.app.noticias.exception.NewsException
import albertojunior.setor0.app.noticias.extension.combine
import albertojunior.setor0.app.noticias.model.District
import albertojunior.setor0.app.noticias.model.news.CoreInformationNewsConverter
import albertojunior.setor0.app.noticias.model.news.News
import albertojunior.setor0.app.noticias.model.news.NewsConverter
import albertojunior.setor0.app.noticias.model.news.NewsDTO
import albertojunior.setor0.app.noticias.model.prompter.PromptCoreInformationNews
import albertojunior.setor0.app.noticias.model.prompter.PromptDistrictNews
import albertojunior.setor0.app.noticias.utils.ContextUtils
import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class NewsViewModel @Inject constructor(
    @Named("Pro") private val generativeModel: GenerativeModel,
    @Named("News-Analyst") private val analystModel: GenerativeModel,
    private val repository: NewsRepository,
    private val resourceProvider: Resources
) : ViewModel() {
    private val channels = District.districts

    private val _currentChannel = MutableLiveData(0)
    val channel = _currentChannel.map { resourceProvider.getString(R.string.news_channel, it, channels[it].name) }

    private val _titleText = MutableLiveData(resourceProvider.getString(R.string.news_empty))
    val titleText: LiveData<String> = _titleText
    private val _newsText = MutableLiveData("")
    val newsText: LiveData<String> = _newsText

    private val _loading = MutableLiveData(false)
    val loadingVisibility = _loading.map { if (it) View.VISIBLE else View.GONE }
    val noticeVisibility = _loading.map { if (!it) View.VISIBLE else View.GONE }
    val channelButtonsEnabled = _loading.map { !it }

    val copyButtonEnabled = combine(_loading, _titleText, _newsText) { isLoad, title, news ->
        val newsEmpty = resourceProvider.getString(R.string.news_empty)

        val safeA = isLoad ?: false
        val safeB = title ?: newsEmpty
        val safeC = news.orEmpty()
        !safeA && safeB.isNotEmpty() && safeB != newsEmpty && safeC.isNotEmpty()
    }

    val refreshButtonEnabled = _loading.map { !it }

    fun beforeChannel() {
        var channel = _currentChannel.value?.minus(1) ?: 0
        if (channel <= -1)
            channel = channels.lastIndex

        _currentChannel.value = channel

        generateNews(channels[channel])
    }

    fun nextChannel() {
        var channel = _currentChannel.value?.plus(1) ?: 0
        if (channel > channels.lastIndex)
            channel = 0

        _currentChannel.value = channel

        generateNews(channels[channel])
    }

    private fun generateNews(district: District) {
        _loading.value = true
        viewModelScope.launch {
            val oldNews = repository.getNews(district.name)
            val prompt = PromptDistrictNews(district, oldNews).mount()

            runCatching {
                println(prompt)
                generativeModel.generateContent(prompt)
            }.onSuccess { response ->
                operateNews(response, district)
            }.onFailure {
                println(it)
                showErrorNews()
            }

            _loading.value = false
        }
    }

    private fun operateNews(
        generatedNews: GenerateContentResponse,
        district: District
    ) {
        runCatching {
            generatedNews.text
                ?.let { news -> NewsConverter.convertTextToNewsDTO(news) }
                ?: throw NewsException.newsIsNull()
        }.onSuccess { auxNews ->
            fetchInformationOnNews(district, auxNews)
            showNews(auxNews.title, auxNews.news)
        }.onFailure {
            showErrorNews()
        }
    }

    private fun showErrorNews() {
        showNews(
            resourceProvider.getString(R.string.news_error_title),
            resourceProvider.getString(R.string.news_error_body)
        )
    }

    private fun showNews(title: String, news: String) {
        _titleText.value = title
        _newsText.value = news
    }

    private fun fetchInformationOnNews(district: District, news: NewsDTO) {
        viewModelScope.launch {
            runCatching {
                analystModel.generateContent(PromptCoreInformationNews(news).mount())
            }.onSuccess {
                it.text?.let { text ->
                    val coreInfo = CoreInformationNewsConverter.convertTextToCoreInformationNews(text)
                    val element = News(district, news.title, news.news, coreInfo)
                    println("Informações: \n$coreInfo")
                    repository.addNews(element)
                }
            }.onFailure {
                println(it)
            }
        }
    }

    fun refreshNews() {
        val channel = _currentChannel.value ?: return
        generateNews(channels[channel])
    }

    fun copyMessage(context: Context) {
        ContextUtils.copyMessage(context, resourceProvider.getString(R.string.news_text_copied), _newsText.value)
    }
}
