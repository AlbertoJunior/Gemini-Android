package albertojunior.setor0.app.noticias.model.news

import albertojunior.setor0.app.noticias.exception.NewsException
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