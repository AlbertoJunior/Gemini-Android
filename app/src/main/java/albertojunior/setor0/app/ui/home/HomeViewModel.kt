package albertojunior.setor0.app.ui.home

import albertojunior.setor0.app.R
import albertojunior.setor0.app.ai.prompter.PromptWelcome
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
    private val prompter = PromptWelcome().mount()
    private val _welcomeText = MutableLiveData(resources.getString(R.string.home_offline_welcome_message))
    val welcomeText = _welcomeText.map {
        if (it.isNotBlank())
            resources.getString(R.string.home_welcome_message, it)
        else
            resources.getString(R.string.home_error)
    }
    private var hasGeneratedWelcome = false

    fun generateWelcomeText() {
        viewModelScope.launch {
            if (hasGeneratedWelcome)
                return@launch

            runCatching { generativeModel.generateContent(prompter) }
                .onSuccess {
                    _welcomeText.value = it.text
                    hasGeneratedWelcome = true
                }
                .onFailure { _welcomeText.value = resources.getString(R.string.home_offline_welcome_message) }
        }
    }
}