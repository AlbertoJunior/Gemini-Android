package albertojunior.setor0.app.establishment.data.model.districts

import albertojunior.setor0.app.core.data.District
import albertojunior.setor0.app.establishment.data.enums.EstablishmentCharacteristic
import albertojunior.setor0.app.establishment.data.enums.EstablishmentTypes
import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits

internal object PTITSY : EstablishmentDistrict(District.PTITSY) {
    override fun commonCharacteristics() =
        listOf(
            EstablishmentCharacteristic.ESTABELECIMENTOS,
            EstablishmentCharacteristic.CIBERSEGURANCA,
            EstablishmentCharacteristic.INFRAESTRUTURA,
        )

    override fun typesBySize(size: Int) =
        when (size) {
            1 -> listOf(
                EstablishmentTypes.LABORATORIO,
                EstablishmentTypes.MEDTEC
            )

            2 -> listOf(
                EstablishmentTypes.LABORATORIO,
                EstablishmentTypes.MEDTEC,
                EstablishmentTypes.CASSINO_PEQUENO
            )

            3 -> listOf(
                EstablishmentTypes.LABORATORIO,
                EstablishmentTypes.LABORATORIO_GRANDE,
                EstablishmentTypes.MEDTEC
            )

            4 -> listOf(
                EstablishmentTypes.LABORATORIO_GRANDE
            )

            5 -> listOf(
                EstablishmentTypes.LABORATORIO_GRANDE
            )

            else -> emptyList()
        }

    override fun commonGoodTraits() =
        listOf(
            EstablishmentTraits.Good.CibersegurancaMelhorada,
            EstablishmentTraits.Good.FacilidadeAcessoItens,
            EstablishmentTraits.Good.FacilidadeAcessoInformacoes,
        )

    override fun commonBadTraits() =
        listOf(
            EstablishmentTraits.Bad.DependenciaImportacoes,
            EstablishmentTraits.Bad.ProblemasDificeisResolver
        )
}