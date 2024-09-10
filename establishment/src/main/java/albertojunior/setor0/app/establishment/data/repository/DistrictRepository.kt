package albertojunior.setor0.app.establishment.data.repository

import albertojunior.setor0.app.establishment.data.model.EstablishmentDistrict

internal class DistrictRepository {
    companion object {
        private val all = listOf(
            EstablishmentDistrict.ALFIRAN,
            EstablishmentDistrict.AMEISEN,
            EstablishmentDistrict.ARANHAS,
            EstablishmentDistrict.PTITSY,
            EstablishmentDistrict.TOKOJIRAMI,
            EstablishmentDistrict.VYURA,
        )

        fun getAllDistricts() = all
    }
}