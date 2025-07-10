package albertojunior.setor0.app.establishment.data.model.districts

import albertojunior.setor0.app.core.data.District
import albertojunior.setor0.app.establishment.data.enums.EstablishmentCharacteristic
import albertojunior.setor0.app.establishment.data.enums.EstablishmentTypes
import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits

internal object VYURA : EstablishmentDistrict(District.VYURA) {
    override fun commonCharacteristics() =
        listOf(
            EstablishmentCharacteristic.INFRAESTRUTURA,
            EstablishmentCharacteristic.SEGURANCA,
            EstablishmentCharacteristic.CIBERSEGURANCA
        )

    override fun typesBySize(size: Int) =
        when (size) {
            1 -> listOf(
                EstablishmentTypes.LABORATORIO,
                EstablishmentTypes.MEDTEC,
                EstablishmentTypes.CENTRO_PESQUISA_PEQUENO,
            )

            2 -> listOf(
                EstablishmentTypes.LABORATORIO,
                EstablishmentTypes.MEDTEC,
                EstablishmentTypes.CENTRO_PESQUISA_PEQUENO,
            )

            3 -> listOf(
                EstablishmentTypes.LABORATORIO,
                EstablishmentTypes.MEDTEC,
                EstablishmentTypes.CENTRO_PESQUISA_PEQUENO,
                EstablishmentTypes.CENTRO_PESQUISA_MEDIO,
            )

            4 -> listOf(
                EstablishmentTypes.LABORATORIO,
                EstablishmentTypes.MEDTEC,
                EstablishmentTypes.CENTRO_PESQUISA_MEDIO,
                EstablishmentTypes.CENTRO_PESQUISA_GRANDE,
            )

            5 -> listOf(
                EstablishmentTypes.LABORATORIO,
                EstablishmentTypes.MEDTEC,
                EstablishmentTypes.CENTRO_PESQUISA_GRANDE,
                EstablishmentTypes.FABRICA_APRIMORAMENTOS,
                EstablishmentTypes.FABRICA_SINTETICOS
            )

            else -> emptyList()
        }

    override fun commonGoodTraits() =
        listOf(
            EstablishmentTraits.Good.IncentivosGovernamentais,
            EstablishmentTraits.Good.BemFeito,
            EstablishmentTraits.Good.BemEquipado,
        )

    override fun commonBadTraits() =
        listOf(
            EstablishmentTraits.Bad.ControleGovernamental,
            EstablishmentTraits.Bad.Insatisfacao,
            EstablishmentTraits.Bad.ProgressoLento
        )
}