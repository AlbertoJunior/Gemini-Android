package albertojunior.setor0.app.establishment.data.repository

import albertojunior.setor0.app.establishment.data.model.districts.ALFIRAN
import albertojunior.setor0.app.establishment.data.model.districts.AMEISEN
import albertojunior.setor0.app.establishment.data.model.districts.ARANHAS
import albertojunior.setor0.app.establishment.data.model.districts.PTITSY
import albertojunior.setor0.app.establishment.data.model.districts.TOKOJIRAMI
import albertojunior.setor0.app.establishment.data.model.districts.VYURA

internal class DistrictRepository {
    companion object {
        private val all = listOf(
            ALFIRAN,
            AMEISEN,
            ARANHAS,
            PTITSY,
            TOKOJIRAMI,
            VYURA,
        )

        fun getAllDistricts() = all
    }
}