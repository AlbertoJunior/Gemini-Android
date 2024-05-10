package albertojunior.setor0.app.noticias.ui.news

import albertojunior.setor0.app.noticias.model.news.News

interface NewsRepository {
    val lastNews: HashMap<String, MutableList<News>>
    suspend fun addNews(news: News)
    suspend fun getNews(districtName: String): List<News>
    suspend fun getAllNews(): List<News>
}