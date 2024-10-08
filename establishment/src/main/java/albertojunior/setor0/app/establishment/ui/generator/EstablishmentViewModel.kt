package albertojunior.setor0.app.establishment.ui.generator

import albertojunior.setor0.app.establishment.R
import albertojunior.setor0.app.establishment.data.model.Establishment
import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits
import albertojunior.setor0.app.establishment.data.repository.DistrictRepository
import albertojunior.setor0.app.establishment.use_case.EstablishmentGeneratorUseCase
import albertojunior.setor0.app.establishment.utils.ContextUtils
import albertojunior.setor0.app.establishment.utils.RandomUtils
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class EstablishmentViewModel @Inject constructor(
    private val resourceProvider: Resources,
    private val establishmentGeneratorUseCase: EstablishmentGeneratorUseCase
) : ViewModel() {

    private val _establishment = MutableLiveData<Establishment?>(null)
    val establishment: LiveData<Establishment?> = _establishment

    val district = _establishment.map {
        it?.let { district ->
            resourceProvider.getString(R.string.information_district, district.bairro)
        } ?: resourceProvider.getString(R.string.district)
    }
    val size = _establishment.map {
        it?.let { district ->
            resourceProvider.getString(R.string.information_size, district.tamanho)
        } ?: resourceProvider.getString(R.string.size)
    }
    val type = _establishment.map {
        it?.let { district ->
            resourceProvider.getString(R.string.information_type, district.tipo)
        } ?: resourceProvider.getString(R.string.type)
    }
    val treasure = _establishment.map {
        it?.let { district ->
            resourceProvider.getString(R.string.information_treasure, district.tesouroGerado)
        } ?: resourceProvider.getString(R.string.treasure)
    }
    val characteristics = _establishment.map {
        it?.caracteristicas?.map { characteristic -> characteristic.toString() }?.sorted() ?: emptyList()
    }
    val goodTraits: LiveData<List<EstablishmentTraits>> = _establishment.map {
        it?.tracosBons?.map { trait -> trait.copy() } ?: emptyList()
    }
    val badTraits: LiveData<List<EstablishmentTraits>> = _establishment.map {
        it?.tracosRuins?.map { trait -> trait.copy() } ?: emptyList()
    }

    fun generate(
        size: String? = null,
        district: String? = null
    ) {
        val sizeIntOrNull = size.takeIf { it?.isNotBlank() == true }?.toIntOrNull()?.takeIf { it != 0 }
        val selectedSize = sizeIntOrNull ?: RandomUtils.randomize(6, 1)
        val foundedDistrict = DistrictRepository.getAllDistricts().firstOrNull { it.name == district }
        _establishment.value = establishmentGeneratorUseCase(selectedSize, foundedDistrict)
    }

    fun shareNews(context: Context) {
        establishment.value?.let {
            val json = GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(Establishment.EstablishmentCleaned.fromEstablishment(it))
            ContextUtils.copyMessage(context, "Estabelecimento copiado", json)
        }
    }
}