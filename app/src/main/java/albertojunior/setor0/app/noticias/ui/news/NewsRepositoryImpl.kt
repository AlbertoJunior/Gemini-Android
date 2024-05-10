package albertojunior.setor0.app.noticias.ui.news

import albertojunior.setor0.app.noticias.model.news.News

class NewsRepositoryImpl : NewsRepository {
    override val lastNews = hashMapOf<String, MutableList<News>>()

    override suspend fun addNews(news: News) {
        val name = news.district.name
        if (lastNews[name] == null)
            lastNews[name] = ArrayList()
        lastNews[name]?.add(news)
    }

    override suspend fun getNews(districtName: String): List<News> {
        return lastNews[districtName] ?: listOf()
    }

    override suspend fun getAllNews(): List<News> {
        return mutableListOf<News>().apply {
            lastNews.values.forEach {
                addAll(it)
            }
        }
    }
}