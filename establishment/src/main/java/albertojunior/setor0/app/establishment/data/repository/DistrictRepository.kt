package albertojunior.setor0.app.establishment.data.repository

import albertojunior.setor0.app.establishment.data.model.District

class DistrictRepository {
    companion object {
        private val all = listOf(
            District.ALFIRAN,
            District.AMEISEN,
            District.ARANHAS,
            District.PTITSY,
            District.TOKOJIRAMI,
            District.VYURA,
        )

        fun getAllDistricts() = all
    }
}