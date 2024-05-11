package albertojunior.setor0.app.noticias.data.repository

import albertojunior.setor0.app.noticias.data.model.news.News

interface NewsRepository {
    val lastNews: HashMap<String, MutableList<News>>
    suspend fun addNews(news: News)
    suspend fun getNews(districtName: String): List<News>
    suspend fun getAllNews(): List<News>
}