package albertojunior.setor0.app.establishment.use_case

import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits
import albertojunior.setor0.app.establishment.utils.Extensions.takeShuffledByList

internal class SelectBadTraitsUseCase {
    operator fun invoke(
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
}