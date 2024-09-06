package albertojunior.setor0.app.ui.information

import albertojunior.setor0.app.R
import albertojunior.setor0.app.data.model.news.News
import albertojunior.setor0.app.data.repository.NewsRepository
import albertojunior.setor0.app.utils.ContextUtils
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val resourceProvider: Resources
) : ViewModel() {
    private val _news = MutableLiveData<News>()
    val news: LiveData<News> = _news
    val newsDistrict = _news.map { resourceProvider.getString(R.string.information_district, it.district.name) }
    val newsWho = _news.map { resourceProvider.getString(R.string.information_who, it.coreInformation.who) }
    val newsWhat = _news.map { resourceProvider.getString(R.string.information_what, it.coreInformation.what) }
    val newsWhen = _news.map { resourceProvider.getString(R.string.information_when, it.coreInformation.wheen) }
    val newsHow = _news.map { resourceProvider.getString(R.string.information_how, it.coreInformation.how) }
    val newsResult = _news.map { resourceProvider.getString(R.string.information_result, it.coreInformation.results) }

    private val _back = MutableLiveData<Unit>()
    val back: LiveData<Unit> = _back

    fun back() {
        _back.value = Unit
    }

    fun copyNews(context: Context) {
        _news.value ?: return
        ContextUtils.copyMessage(context, resourceProvider.getString(R.string.news_text_copied), "")
    }

    fun shareNews(context: Context) {
        _news.value ?: return
        ContextUtils.copyMessage(context, resourceProvider.getString(R.string.news_text_copied), "")
    }

    fun setNews(news: News) {
        _news.value = news
    }
}