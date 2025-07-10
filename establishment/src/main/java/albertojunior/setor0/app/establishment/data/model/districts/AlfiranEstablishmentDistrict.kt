package albertojunior.setor0.app.establishment.data.model.districts

import albertojunior.setor0.app.core.data.District
import albertojunior.setor0.app.establishment.data.enums.EstablishmentCharacteristic
import albertojunior.setor0.app.establishment.data.enums.EstablishmentTypes
import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits

internal object ALFIRAN : EstablishmentDistrict(District.ALFIRAN) {
    override fun commonCharacteristics() =
        listOf(
            EstablishmentCharacteristic.SEGURANCA,
            EstablishmentCharacteristic.SERVICOS
        )

    override fun typesBySize(size: Int) =
        when (size) {
            1 -> listOf(
                EstablishmentTypes.CONTAINER_PROSTITUICAO
            )

            2 -> listOf(
                EstablishmentTypes.FABRICA_ARMAS,
                EstablishmentTypes.LOJA_ARMAS
            )

            3 -> listOf(
                EstablishmentTypes.MEDTEC,
                EstablishmentTypes.LOJA_ARMAS
            )

            4 -> listOf(
                EstablishmentTypes.FABRICA_ARMAS_GRANDE,
                EstablishmentTypes.CELULA_ENERGIA,
                EstablishmentTypes.LOJA_ARMAS
            )

            5 -> listOf(
                EstablishmentTypes.USINA_ENERGIA,
                EstablishmentTypes.LOJA_ARMAS
            )

            else -> emptyList()
        }

    override fun commonGoodTraits() =
        listOf(
            EstablishmentTraits.Good.BemEquipado,
            EstablishmentTraits.Good.BemEnergizado,
            EstablishmentTraits.Good.SegurancaMelhorada,
        )

    override fun commonBadTraits() =
        listOf(
            EstablishmentTraits.Bad.CriminalidadeAlta,
            EstablishmentTraits.Bad.DependenciaImportacoes,
            EstablishmentTraits.Bad.InfraestruturaPrecaria
        )
}
