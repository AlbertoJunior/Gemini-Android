package albertojunior.setor0.app.establishment

import albertojunior.setor0.app.establishment.data.model.Characteristic
import albertojunior.setor0.app.establishment.data.model.District
import albertojunior.setor0.app.establishment.data.model.Establishment
import albertojunior.setor0.app.establishment.data.model.EstablishmentCharacteristic
import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits
import albertojunior.setor0.app.establishment.data.model.EstablishmentTypes
import albertojunior.setor0.app.establishment.data.model.SizeInformations
import albertojunior.setor0.app.establishment.data.repository.DistrictRepository

class EstablishmentGenerator {

    fun generate(
        size: Int,
        district: District? = null
    ): Establishment {
        SizeInformations.getSizeInfo(size).also { information ->
            val selectedDistrict = district ?: takeShuffledByList(DistrictRepository.getAllDistricts())
            val type = selectType(information.possibleTypes, selectedDistrict.typesBySize(size))

            val possibleGoodTraits = mutableListOf<EstablishmentTraits.Good>().apply {
                addAll(information.possibleGoodTraits)
                addAll(type.preferredGoodTraits)
                addAll(selectedDistrict.commonGoodTraits())
            }
            val selectGoodTraits = selectGoodTraits(information.amountGoodTraits, possibleGoodTraits)

            val possibleBadTraits = mutableListOf<EstablishmentTraits.Bad>().apply {
                addAll(information.possibleBadTraits)
                addAll(selectedDistrict.commonBadTraits())
            }
            val selectBadTraits = selectBadTraits(information.amountBadTraits, possibleBadTraits)

            val characteristicLevel = updateCharacteristics(
                information.characteristicsPoints,
                information.characteristics,
                selectedDistrict.getRandomizedPreferredCharacteristic(),
                selectGoodTraits
            )

            val calculatedTreasure = calculateTreasure(
                characteristicLevel,
                information.baseTreasure,
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

    private fun selectType(
        typesBySize: List<EstablishmentTypes>,
        typesByDistrict: List<EstablishmentTypes>,
    ): EstablishmentTypes {
        val possibleTypes = mutableListOf<EstablishmentTypes>().apply {
            addAll(typesBySize)
            addAll(typesByDistrict)
        }
        return takeShuffledByList(possibleTypes)
    }

    private fun selectGoodTraits(
        amount: Int,
        goodTraits: List<EstablishmentTraits.Good>
    ): List<EstablishmentTraits.Good> {
        val possibleList = mutableListOf<EstablishmentTraits.Good>().apply {
            addAll(goodTraits)
            removeAll { it.preReq().isNotEmpty() }
        }

        val selected = mutableListOf<EstablishmentTraits.Good>()

        repeat(amount) {
            val trait = takeShuffledByList(possibleList)
            selected.add(trait)

            // add trait combined with more probability
            trait.enableTrait().forEach {
                possibleList.add(it)
                possibleList.add(it)
                possibleList.add(it)
            }

            if (trait.onlyOne())
                possibleList.removeAll { it == trait }
        }

        return selected
    }

    private fun selectBadTraits(
        amount: Int,
        badTraits: List<EstablishmentTraits.Bad>
    ): List<EstablishmentTraits.Bad> {
        val possibleList = mutableListOf<EstablishmentTraits.Bad>().apply {
            addAll(badTraits)
        }

        val selected = mutableListOf<EstablishmentTraits.Bad>()

        repeat(amount) {
            val trait = takeShuffledByList(possibleList)
            selected.add(trait)

            if (trait.onlyOne())
                possibleList.removeAll { it == trait }
        }

        return selected
    }

    private fun calculateTreasure(
        establishmentCharacteristics: List<Characteristic>,
        treasure: Int,
        badTraits: List<EstablishmentTraits.Bad>,
        establishmentSize: Int
    ): Int {
        val characteristicLevel = establishmentCharacteristics
            .firstOrNull { it.characteristic == EstablishmentCharacteristic.CLIENTES }?.level ?: 0

        val tax = if (badTraits.contains(EstablishmentTraits.Bad.Impostos))
            EstablishmentTraits.Bad.Impostos.getValue(establishmentSize)
        else
            0

        return characteristicLevel + treasure - tax
    }

    private fun updateCharacteristics(
        points: Int,
        possibleCharacteristics: List<EstablishmentCharacteristic>,
        districtRandomizedCharacteristic: EstablishmentCharacteristic,
        goodTraits: List<EstablishmentTraits.Good>
    ): List<Characteristic> {
        val updateCharacteristics = mutableListOf<EstablishmentCharacteristic>()
            .apply {
                addAll(possibleCharacteristics)
                add(districtRandomizedCharacteristic)
                goodTraits.mapTo(this) { it.getRandomizedPreferredCharacteristic() }
            }

        updateCharacteristics
            .distinctBy { it.name }
            .shuffled()
            .take(possibleCharacteristics.size)
            .toMutableList()
            .run {
                if (!contains(EstablishmentCharacteristic.SERVICOS)) {
                    removeLastOrNull()
                    add(EstablishmentCharacteristic.SERVICOS)
                }

                updateCharacteristics.removeIf { !this.contains(it) }
            }

        val mutableMap = updateCharacteristics
            .distinctBy { it.name }
            .associateWithTo(mutableMapOf()) { 1 }

        repeat(points) { _ ->
            val characteristic = updateCharacteristics.shuffled()[0]

            val level = mutableMap[characteristic] ?: 1
            if (level < 5)
                updateCharacteristics.add(characteristic)

            mutableMap[characteristic] = level + 1
            if (mutableMap[characteristic] == 5)
                updateCharacteristics.removeAll { it == characteristic }
        }

        return mutableMap.map { (k, v) -> Characteristic(k, v) }
    }

    private fun <T> takeShuffledByList(list: List<T>) = list.shuffled().take(1)[0]
}
