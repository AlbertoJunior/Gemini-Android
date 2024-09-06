package albertojunior.setor0.app.data.model.coreinformation

import albertojunior.setor0.app.data.model.news.NewsException
import com.google.gson.Gson

object CoreInformationNewsConverter {
    private fun clean(news: String): String {
        return news
            .replace("```json\n", "")
            .replace("```", "")
            .replace("*", "")
    }

    fun convertTextToCoreInformationNews(text: String): CoreInformationNews {
        runCatching {
            val textCleaned = clean(text)
            Gson().fromJson(textCleaned, CoreInformationNews::class.java)
        }.onSuccess {
            return it
        }
        throw NewsException.conversionFailure()
    }
}