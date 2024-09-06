package albertojunior.setor0.app.establishment.use_case

import albertojunior.setor0.app.establishment.data.model.Characteristic
import albertojunior.setor0.app.establishment.data.enums.EstablishmentCharacteristic
import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits

internal class UpdateCharacteristicsUseCase {
    operator fun invoke(
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
}