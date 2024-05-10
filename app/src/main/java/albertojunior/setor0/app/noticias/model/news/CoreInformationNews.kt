package albertojunior.setor0.app.noticias.model.news

import javax.inject.Named

data class CoreInformationNews(
    val who: String = "",
    val what: String = "",
    @Named("when") val wheen: String = "",
    val how: String = "",
    val results: String = ""
) {
    override fun toString(): String {
        return "Quem: $who\n" +
                "O que: $what\n" +
                "Como: $how\n" +
                "Consequências: $results"
    }
}