package albertojunior.setor0.app.noticias.ui.home

import albertojunior.setor0.app.noticias.R
import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Named("Welcome") private val generativeModel: GenerativeModel,
    private val resources: Resources
) : ViewModel() {
    private val _welcomeText = MutableLiveData<String>()
    val welcomeText = _welcomeText.map {
        if (it.isNotBlank())
            resources.getString(R.string.home_welcome_message, it)
        else
            resources.getString(R.string.home_error)
    }

    fun generateWelcomeText() {
        viewModelScope.launch {
            runCatching {
                generativeModel.generateContent()
            }.onSuccess {
                _welcomeText.value = it.text
            }.onFailure {
                _welcomeText.value = ""
            }
        }
    }
}