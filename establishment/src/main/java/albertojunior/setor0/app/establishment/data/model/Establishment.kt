package albertojunior.setor0.app.establishment.data.model

import java.util.GregorianCalendar

internal data class Establishment(
    val bairro: String,
    val tamanho: Int,
    val caracteristicas: List<Characteristic>,
    val tracosBons: List<EstablishmentTraits.Good>,
    val tracosRuins: List<EstablishmentTraits.Bad>,
    val tesouroGerado: Int,
    val tipo: String,
    val id: String = "${bairro}_${tamanho}_${GregorianCalendar().timeInMillis}"
) {

    data class EstablishmentCleaned(
        val bairro: String,
        val tamanho: Int,
        val caracteristicas: List<String>,
        val tracosBons: List<String>,
        val tracosRuins: List<String>,
        val tesouroGerado: Int,
        val descricao: String
    ) {
        companion object {
            fun fromEstablishment(establishment: Establishment) =
                EstablishmentCleaned(
                    establishment.bairro,
                    establishment.tamanho,
                    establishment.caracteristicas.map { it.toString() },
                    establishment.tracosBons.map { it.name },
                    establishment.tracosRuins.map { it.name },
                    establishment.tesouroGerado,
                    establishment.tipo
                )
        }
    }
}

