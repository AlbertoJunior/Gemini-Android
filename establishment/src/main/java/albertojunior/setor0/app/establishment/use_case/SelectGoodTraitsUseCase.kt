package albertojunior.setor0.app.establishment.use_case

import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits
import albertojunior.setor0.app.establishment.utils.Extensions.takeShuffledByList

internal class SelectGoodTraitsUseCase {
    operator fun invoke(
        amount: Int,
        goodTraits: List<EstablishmentTraits.Good>
    ): List<EstablishmentTraits.Good> {
        val possibleList = mutableListOf<EstablishmentTraits.Good>().apply {
            addAll(goodTraits)
            removeAll { it.prerequisites().isNotEmpty() }
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
}