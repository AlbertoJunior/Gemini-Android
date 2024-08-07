package albertojunior.setor0.app.establishment.ui

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
internal class ViewModelDelegate<VM : ViewModel>(
    private val fragment: Fragment,
) : LifecycleEventObserver {
    private lateinit var value: VM

    init {
        fragment.lifecycle.addObserver(this)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): VM {
        return value
    }

    inner class NeedResourceViewModelFactory(
        private val resources: Resources
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EstablishmentViewModel::class.java)) {
                return EstablishmentViewModel(resources) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                try {
                    value = ViewModelProvider(
                        fragment,
                        NeedResourceViewModelFactory(fragment.resources)
                    )[EstablishmentViewModel::class.java] as VM
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            else -> Unit
        }
    }
}