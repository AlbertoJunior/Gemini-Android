package albertojunior.setor0.app.establishment.use_case

import albertojunior.setor0.app.establishment.data.enums.EstablishmentTypes
import albertojunior.setor0.app.establishment.utils.Extensions.takeShuffledByList

internal class SelectTypeUseCase {
    operator fun invoke(
        typesBySize: List<EstablishmentTypes>,
        typesByDistrict: List<EstablishmentTypes>,
    ): EstablishmentTypes {
        val possibleTypes = mutableListOf<EstablishmentTypes>().apply {
            addAll(typesBySize)
            addAll(typesByDistrict)
        }
        return takeShuffledByList(possibleTypes)
    }
}