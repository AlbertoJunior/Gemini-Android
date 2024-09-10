package albertojunior.setor0.app.establishment.use_case

import albertojunior.setor0.app.establishment.data.model.EstablishmentDistrict
import albertojunior.setor0.app.establishment.data.model.Establishment
import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits
import albertojunior.setor0.app.establishment.data.enums.EstablishmentTypes
import albertojunior.setor0.app.establishment.data.model.SizeInformation
import albertojunior.setor0.app.establishment.data.repository.DistrictRepository
import albertojunior.setor0.app.establishment.utils.Extensions.takeShuffledByList

internal class EstablishmentGeneratorUseCase(
    val selectTypeUseCase: SelectTypeUseCase,
    val treasureCalculateUseCase: TreasureCalculateUseCase,
    val selectGoodTraitUseCase: SelectGoodTraitsUseCase,
    val selectBadTraitUseCase: SelectBadTraitsUseCase,
    val updateCharacteristicsUseCase: UpdateCharacteristicsUseCase
) {

    companion object {
        val instance = EstablishmentGeneratorUseCase(
            SelectTypeUseCase(),
            TreasureCalculateUseCase(),
            SelectGoodTraitsUseCase(),
            SelectBadTraitsUseCase(),
            UpdateCharacteristicsUseCase()
        )
    }

    operator fun invoke(
        size: Int,
        district: EstablishmentDistrict? = null
    ): Establishment {
        SizeInformation.getSizeInfo(size).also { information ->
            val selectedDistrict = district ?: takeShuffledByList(DistrictRepository.getAllDistricts())
            val type = selectTypeUseCase(
                information.possibleTypes,
                selectedDistrict.typesBySize(size)
            )

            val selectGoodTraits = selectGoodTraits(
                information,
                type,
                selectedDistrict
            )

            val selectBadTraits = selectBadTraits(
                information,
                selectedDistrict,
                selectGoodTraits
            )

            val characteristicLevel = updateCharacteristicsUseCase(
                information.characteristicsPoints,
                information.characteristics,
                selectedDistrict.getRandomizedPreferredCharacteristic(),
                selectGoodTraits
            )

            val calculatedTreasure = treasureCalculateUseCase(
                characteristicLevel,
                information.baseTreasure,
                selectGoodTraits,
                selectBadTraits,
                size
            )

            return Establishment(
                selectedDistrict.name,
                size,
                characteristicLevel.toList(),
                selectGoodTraits,
                selectBadTraits,
                calculatedTreasure,
                type.description
            )
        }
    }

    private fun selectGoodTraits(
        information: SizeInformation,
        type: EstablishmentTypes,
        selectedDistrict: EstablishmentDistrict
    ): List<EstablishmentTraits.Good> {
        val possibleGoodTraits = mutableListOf<EstablishmentTraits.Good>()
            .apply {
                addAll(information.possibleGoodTraits)
                addAll(type.preferredGoodTraits)
                addAll(selectedDistrict.commonGoodTraits())
            }
            .toList()

        return selectGoodTraitUseCase(
            information.amountGoodTraits,
            possibleGoodTraits
        )
    }

    private fun selectBadTraits(
        information: SizeInformation,
        selectedDistrict: EstablishmentDistrict,
        selectGoodTraits: List<EstablishmentTraits.Good>
    ): List<EstablishmentTraits.Bad> {
        val possibleBadTraits = mutableListOf<EstablishmentTraits.Bad>()
            .apply {
                addAll(information.possibleBadTraits)
                addAll(selectedDistrict.commonBadTraits())
                removeAll {
                    val prerequisites = it.prerequisites()
                    prerequisites.isNotEmpty() && !selectGoodTraits.containsAll(prerequisites)
                }
                filter { it.prerequisites().isNotEmpty() }.forEach { add(it) }
            }
            .toList()
        return selectBadTraitUseCase(
            information.amountBadTraits,
            possibleBadTraits
        )
    }
}