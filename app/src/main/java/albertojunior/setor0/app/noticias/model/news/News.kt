package albertojunior.setor0.app.noticias.model.news

import albertojunior.setor0.app.noticias.model.District

data class News(
    val district: District,
    val title: String,
    val body: String,
    val coreInformation: CoreInformationNews,
    val beforeNews: News? = null
)