package albertojunior.setor0.app.noticias.ui.information

import albertojunior.setor0.app.noticias.R
import albertojunior.setor0.app.noticias.ui.news.NewsRepository
import albertojunior.setor0.app.noticias.utils.ContextUtils
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val resourceProvider: Resources
) : ViewModel() {
    fun copyNews(context: Context) {
        ContextUtils.copyMessage(context, resourceProvider.getString(R.string.news_text_copied), "")
    }

    fun shareNews(context: Context) {
        ContextUtils.copyMessage(context, resourceProvider.getString(R.string.news_text_copied), "")
    }
}