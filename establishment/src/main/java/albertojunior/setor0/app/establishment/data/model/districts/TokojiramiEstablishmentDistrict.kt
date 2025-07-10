package albertojunior.setor0.app.establishment.data.model.districts

import albertojunior.setor0.app.core.data.District
import albertojunior.setor0.app.establishment.data.enums.EstablishmentCharacteristic
import albertojunior.setor0.app.establishment.data.enums.EstablishmentTypes
import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits

internal object TOKOJIRAMI : EstablishmentDistrict(District.TOKOJIRAMI) {
    override fun commonCharacteristics() =
        listOf(
            EstablishmentCharacteristic.CLIENTES,
            EstablishmentCharacteristic.ESTABELECIMENTOS,
            EstablishmentCharacteristic.SEGURANCA
        )

    override fun typesBySize(size: Int) =
        when (size) {
            1 -> listOf(
                EstablishmentTypes.MEDTEC,
                EstablishmentTypes.LABORATORIO,
                EstablishmentTypes.CONTAINER_PROSTITUICAO
            )

            2 -> listOf(
                EstablishmentTypes.LABORATORIO,
                EstablishmentTypes.CASA_PROSTITUICAO,
                EstablishmentTypes.LOJA_ARMAS,
                EstablishmentTypes.CASSINO_PEQUENO
            )

            3 -> listOf(
                EstablishmentTypes.FERREIRO,
                EstablishmentTypes.CASA_PROSTITUICAO,
                EstablishmentTypes.CASA_CARNE,
                EstablishmentTypes.LOJA_GRANDE,
                EstablishmentTypes.LOJA_ARMAS
            )

            4 -> listOf(
                EstablishmentTypes.FERREIRO,
                EstablishmentTypes.GALPAO,
                EstablishmentTypes.GALPAO_GRANDE
            )

            5 -> listOf(
                EstablishmentTypes.GALPAO_GRANDE
            )

            else -> emptyList()
        }

    override fun commonGoodTraits() =
        listOf(
            EstablishmentTraits.Good.IncentivosGovernamentais,
            EstablishmentTraits.Good.SegurancaMelhorada,
            EstablishmentTraits.Good.FacilidadeAcessoItens,
        )

    override fun commonBadTraits() =
        listOf(
            EstablishmentTraits.Bad.CriminalidadeAlta,
            EstablishmentTraits.Bad.ControleGovernamental,
            EstablishmentTraits.Bad.Impostos
        )
}