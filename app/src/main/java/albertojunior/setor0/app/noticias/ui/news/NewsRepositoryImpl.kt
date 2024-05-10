package albertojunior.setor0.app.noticias.ui.news

import albertojunior.setor0.app.noticias.model.News

class NewsRepositoryImpl : NewsRepository {
    override val lastNews = hashMapOf<String, MutableList<News>>()

    override fun addNews(news: News) {
        val name = news.district.name
        if (lastNews[name] == null)
            lastNews[name] = ArrayList()
        lastNews[name]?.add(news)
    }

    override fun getNews(districtName: String): List<News> {
        return lastNews[districtName] ?: listOf()
    }
}