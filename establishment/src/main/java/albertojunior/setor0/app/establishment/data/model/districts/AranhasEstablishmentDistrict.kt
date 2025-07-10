package albertojunior.setor0.app.establishment.data.model.districts

import albertojunior.setor0.app.core.data.District
import albertojunior.setor0.app.establishment.data.enums.EstablishmentCharacteristic
import albertojunior.setor0.app.establishment.data.enums.EstablishmentTypes
import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits

internal object ARANHAS : EstablishmentDistrict(District.ARANHAS) {
    override fun commonCharacteristics() =
        listOf(
            EstablishmentCharacteristic.CLIENTES,
            EstablishmentCharacteristic.SERVICOS,
            EstablishmentCharacteristic.ESTABELECIMENTOS,
        )

    override fun typesBySize(size: Int) =
        when (size) {
            1 -> listOf(
                EstablishmentTypes.CONTAINER_PROSTITUICAO
            )

            2 -> listOf(
                EstablishmentTypes.LOJA_CARROS,
                EstablishmentTypes.LOJA_ARMAS,
                EstablishmentTypes.CASSINO_PEQUENO
            )

            3 -> listOf(
                EstablishmentTypes.LOJA_GRANDE,
                EstablishmentTypes.LOJA_ARMAS,
                EstablishmentTypes.CASSINO_PEQUENO
            )

            4 -> listOf(
                EstablishmentTypes.LOJA_CARROS_LUXO,
                EstablishmentTypes.CASSINO,
                EstablishmentTypes.GALPAO,
                EstablishmentTypes.GALPAO_GRANDE
            )

            5 -> listOf(
                EstablishmentTypes.LOJA_CARROS_GRANDE,
                EstablishmentTypes.GALPAO_GRANDE
            )

            else -> emptyList()
        }

    override fun commonGoodTraits() =
        listOf(
            EstablishmentTraits.Good.Autogestao,
            EstablishmentTraits.Good.FonteTesouro,
            EstablishmentTraits.Good.FacilidadeAcessoInformacoes,
        )

    override fun commonBadTraits() =
        listOf(
            EstablishmentTraits.Bad.CriminalidadeAlta,
            EstablishmentTraits.Bad.BlackoutsConstantes,
            EstablishmentTraits.Bad.Impostos
        )
}