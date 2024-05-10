package albertojunior.setor0.app.noticias.ui.news

import albertojunior.setor0.app.noticias.model.News

interface NewsRepository {
    val lastNews: HashMap<String, MutableList<News>>
    fun addNews(news: News)
    fun getNews(districtName: String): List<News>
}