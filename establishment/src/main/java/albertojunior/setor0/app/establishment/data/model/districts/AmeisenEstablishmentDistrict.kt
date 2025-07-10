package albertojunior.setor0.app.establishment.data.model.districts

import albertojunior.setor0.app.core.data.District
import albertojunior.setor0.app.establishment.data.enums.EstablishmentCharacteristic
import albertojunior.setor0.app.establishment.data.enums.EstablishmentTypes
import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits

internal object AMEISEN : EstablishmentDistrict(District.AMEISEN) {
    override fun commonCharacteristics() =
        listOf(
            EstablishmentCharacteristic.CLIENTES,
            EstablishmentCharacteristic.SERVICOS,
            EstablishmentCharacteristic.INFRAESTRUTURA,
        )

    override fun typesBySize(size: Int) =
        when (size) {
            1 -> listOf(
                EstablishmentTypes.CONTAINER_PROSTITUICAO
            )

            2 -> listOf(
                EstablishmentTypes.CASA_PROSTITUICAO
            )

            3 -> listOf(
                EstablishmentTypes.CASA_PROSTITUICAO_LUXO
            )

            4 -> listOf(
                EstablishmentTypes.FABRICA_ANDROIDES,
                EstablishmentTypes.CELULA_ENERGIA,
                EstablishmentTypes.CASA_PROSTITUICAO_LUXO
            )

            5 -> listOf(
                EstablishmentTypes.USINA_ENERGIA,
                EstablishmentTypes.CASA_PROSTITUICAO_LUXO
            )

            else -> emptyList()
        }

    override fun commonGoodTraits() =
        listOf(
            EstablishmentTraits.Good.FonteTesouro,
            EstablishmentTraits.Good.BemEnergizado,
            EstablishmentTraits.Good.FacilidadeAcessoInformacoes,
        )

    override fun commonBadTraits() =
        listOf(
            EstablishmentTraits.Bad.Insatisfacao,
            EstablishmentTraits.Bad.MaFama,
            EstablishmentTraits.Bad.DificilDefender
        )
}