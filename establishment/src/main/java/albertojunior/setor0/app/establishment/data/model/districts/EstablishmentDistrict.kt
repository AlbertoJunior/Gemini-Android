package albertojunior.setor0.app.establishment.data.model.districts

import albertojunior.setor0.app.core.data.District
import albertojunior.setor0.app.establishment.data.enums.EstablishmentCharacteristic
import albertojunior.setor0.app.establishment.data.enums.EstablishmentTypes
import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits

internal sealed class EstablishmentDistrict(
    district: District
) {
    val name = district.name

    override fun toString() = name

    abstract fun typesBySize(size: Int): List<EstablishmentTypes>

    open fun commonCharacteristics(): List<EstablishmentCharacteristic> = emptyList()

    open fun commonGoodTraits(): List<EstablishmentTraits.Good> = emptyList()

    open fun commonBadTraits(): List<EstablishmentTraits.Bad> = emptyList()

    fun getRandomizedPreferredCharacteristic() = commonCharacteristics().shuffled().take(1)[0]
}