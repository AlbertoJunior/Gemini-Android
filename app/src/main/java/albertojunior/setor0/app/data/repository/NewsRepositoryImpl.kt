package albertojunior.setor0.app.data.repository

import albertojunior.setor0.app.data.model.news.News
import com.google.gson.Gson

class NewsRepositoryImpl : NewsRepository {
    override val lastNews = hashMapOf<String, MutableList<News>>()

    init {
        populate()
    }

    private fun populate() {
        val gson = Gson()
        NewsDatabaseMock.mocks.forEach {
            runCatching { gson.fromJson(it, News::class.java) }
                .onSuccess { news -> addNewsAtomic(news) }
        }
    }

    /**
    Only for mocks
     */
    private fun addNewsAtomic(news: News) {
        val name = news.district.name
        if (lastNews[name] == null)
            lastNews[name] = ArrayList()
        lastNews[name]?.add(news)
    }

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