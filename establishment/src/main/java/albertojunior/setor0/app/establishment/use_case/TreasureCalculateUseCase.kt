package albertojunior.setor0.app.establishment.use_case

import albertojunior.setor0.app.establishment.data.model.Characteristic
import albertojunior.setor0.app.establishment.data.enums.EstablishmentCharacteristic
import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits

internal class TreasureCalculateUseCase {
    operator fun invoke(
        establishmentCharacteristics: List<Characteristic>,
        treasure: Int,
        goodTraits: List<EstablishmentTraits.Good>,
        badTraits: List<EstablishmentTraits.Bad>,
        establishmentSize: Int
    ): Int {
        val characteristicLevel = establishmentCharacteristics
            .firstOrNull { it.characteristic == EstablishmentCharacteristic.CLIENTES }?.level
            ?: 0

        val amountTaxes = badTraits.count { it == EstablishmentTraits.Bad.Impostos }
        val tax = EstablishmentTraits.Bad.Impostos.getValue(establishmentSize) * amountTaxes

        val amountMineOfTreasure = goodTraits.count { it == EstablishmentTraits.Good.FonteTesouro }

        val treasurePerClient = 2
        return (characteristicLevel * treasurePerClient) * amountMineOfTreasure + treasure - tax
    }
}