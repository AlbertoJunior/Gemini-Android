package albertojunior.setor0.app.noticias.model

data class News(
    val district: District,
    val title: String,
    val news: String,
    val coreInformation: String,
    val beforeNews: News? = null
)