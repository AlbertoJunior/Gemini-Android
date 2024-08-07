package albertojunior.setor0.app.establishment.data.model

sealed class SizeInformations(
    val characteristics: List<EstablishmentCharacteristic>,
    val characteristicsPoints: Int,
    val possibleGoodTraits: List<EstablishmentTraits.Good>,
    val amountGoodTraits: Int,
    val possibleBadTraits: List<EstablishmentTraits.Bad>,
    val amountBadTraits: Int,
    val possibleTypes: List<EstablishmentTypes>,
    val baseTreasure: Int
) {
    private data object EstablishmentSize1 : SizeInformations(
        characteristics = EstablishmentCharacteristic.obtainCharacteristics(1),
        characteristicsPoints = 0,
        possibleGoodTraits = EstablishmentTraits.Good.traits.filterNot {
            it == EstablishmentTraits.Good.Influencia ||
                    it == EstablishmentTraits.Good.ProducaoConstante ||
                    it == EstablishmentTraits.Good.PossuiTerminal
        },
        amountGoodTraits = 1,
        possibleBadTraits = EstablishmentTraits.Bad.traits,
        amountBadTraits = 1,
        possibleTypes = listOf(
            EstablishmentTypes.BAR,
            EstablishmentTypes.GALPAO_PEQUENO,
            EstablishmentTypes.RESTAURANTE,
            EstablishmentTypes.LOJA_PEQUENA,
            EstablishmentTypes.OFICINA_PEQUENA
        ),
        baseTreasure = 2
    )

    private data object EstablishmentSize2 : SizeInformations(
        characteristics = EstablishmentCharacteristic.obtainCharacteristics(2),
        characteristicsPoints = 1,
        possibleGoodTraits = EstablishmentTraits.Good.traits.filterNot { it == EstablishmentTraits.Good.Influencia },
        amountGoodTraits = 2,
        possibleBadTraits = EstablishmentTraits.Bad.traits,
        amountBadTraits = 1,
        possibleTypes = listOf(
            EstablishmentTypes.BAR,
            EstablishmentTypes.GALPAO_PEQUENO,
            EstablishmentTypes.GALPAO,
            EstablishmentTypes.RESTAURANTE,
            EstablishmentTypes.BOATE,
            EstablishmentTypes.LOJA,
            EstablishmentTypes.RESTAURANTE,
            EstablishmentTypes.OFICINA,
            EstablishmentTypes.CASA_APOSTAS
        ),
        baseTreasure = 5
    )

    private data object EstablishmentSize3 : SizeInformations(
        characteristics = EstablishmentCharacteristic.obtainCharacteristics(3),
        characteristicsPoints = 3,
        possibleGoodTraits = EstablishmentTraits.Good.traits.filterNot { it == EstablishmentTraits.Good.Influencia },
        amountGoodTraits = 3,
        possibleBadTraits = EstablishmentTraits.Bad.traits,
        amountBadTraits = 2,
        possibleTypes = listOf(
            EstablishmentTypes.BAR,
            EstablishmentTypes.GALPAO,
            EstablishmentTypes.RESTAURANTE,
            EstablishmentTypes.BOATE_GRANDE,
            EstablishmentTypes.LOJA_GRANDE,
            EstablishmentTypes.OFICINA_GRANDE,
            EstablishmentTypes.RADIO,
        ),
        baseTreasure = 7
    )

    private data object EstablishmentSize4 : SizeInformations(
        characteristics = EstablishmentCharacteristic.obtainCharacteristics(4),
        characteristicsPoints = 5,
        possibleGoodTraits = EstablishmentTraits.Good.traits,
        amountGoodTraits = 4,
        possibleBadTraits = EstablishmentTraits.Bad.traits,
        amountBadTraits = 2,
        possibleTypes = listOf(EstablishmentTypes.FABRICA, EstablishmentTypes.RADIO),
        baseTreasure = 10
    )

    private data object EstablishmentSize5 : SizeInformations(
        characteristics = EstablishmentCharacteristic.obtainCharacteristics(5),
        characteristicsPoints = 7,
        possibleGoodTraits = EstablishmentTraits.Good.traits,
        amountGoodTraits = 5,
        possibleBadTraits = EstablishmentTraits.Bad.traits,
        amountBadTraits = 3,
        possibleTypes = listOf(EstablishmentTypes.FABRICA_GRANDE, EstablishmentTypes.RADIO),
        baseTreasure = 12
    )

    private data object EstablishmentSize6 : SizeInformations(
        characteristics = EstablishmentCharacteristic.obtainCharacteristics(6),
        characteristicsPoints = 9,
        possibleGoodTraits = EstablishmentTraits.Good.traits,
        amountGoodTraits = 7,
        possibleBadTraits = EstablishmentTraits.Bad.traits,
        amountBadTraits = 5,
        possibleTypes = listOf(EstablishmentTypes.FAVELA),
        baseTreasure = 15
    )

    companion object {
        fun getSizeInfo(size: Int): SizeInformations {
            return when (size) {
                1 -> EstablishmentSize1
                2 -> EstablishmentSize2
                3 -> EstablishmentSize3
                4 -> EstablishmentSize4
                5 -> EstablishmentSize5
                6 -> EstablishmentSize6
                else -> throw IllegalArgumentException("Tamanho inválido")
            }
        }
    }
}