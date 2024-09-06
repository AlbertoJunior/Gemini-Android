package albertojunior.setor0.app.ui.features

import albertojunior.setor0.app.data.model.features.Feature
import albertojunior.setor0.app.data.model.features.FeatureView
import albertojunior.setor0.app.utils.Event
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeaturesViewModel @Inject constructor(
    private val resources: Resources
) : ViewModel() {
    private val _features = MutableLiveData<List<FeatureView>>()
    val features: LiveData<List<FeatureView>> = _features

    private val _featureClicked = MutableLiveData<Event<IdNavigation>>()
    val featureClicked: LiveData<Event<IdNavigation>> = _featureClicked

    private val _hideNavBar = MutableLiveData<Event<Boolean>>()
    val hideNavBar: LiveData<Event<Boolean>> = _hideNavBar

    fun fetchFeatures() {
        _features.value = Feature.values.map { it.toFeatureView(resources) }
    }

    fun onFeatureClick(feature: FeatureView) {
        _hideNavBar.value = Event(feature.hideNav)
        _featureClicked.value = Event(feature.navigation)
    }
}