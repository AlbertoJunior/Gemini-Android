package albertojunior.setor0.app.data.model.news

import com.google.gson.Gson

object NewsConverter {
    private fun clean(news: String): String {
        return news
            .replace("```json\n", "")
            .replace("```", "")
            .replace("android", "androide")
            .replace("Android", "Androide")
            .replace("*", "")
    }

    fun convertTextToNewsDTO(text: String): NewsDTO {
        val newsText = clean(text)

        runCatching {
            Gson().fromJson(newsText, NewsDTO::class.java)
        }.onSuccess {
            return it
        }.onFailure {
            val firstMark = newsText.indexOf("\n\n")
            val title = newsText.substring(0, firstMark)
            val news = newsText.substring(title.length, newsText.length)

            if (title.isNotBlank() && news.isNotBlank()) {
                return NewsDTO(title, news)
            }
        }

        throw NewsException.conversionFailure()
    }
}