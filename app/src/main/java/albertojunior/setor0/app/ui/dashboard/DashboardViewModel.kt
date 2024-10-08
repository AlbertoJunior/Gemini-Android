package albertojunior.setor0.app.ui.dashboard

import albertojunior.setor0.app.data.model.news.News
import albertojunior.setor0.app.data.repository.NewsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {
    private val _items = MutableLiveData<List<News>>()
    val items: LiveData<List<News>> = _items.map { items ->
        items.sortedWith { item1, item2 -> item1.district.name.compareTo(item2.district.name) }
    }

    private fun getItems() {
        viewModelScope.launch { _items.value = repository.getAllNews() }
    }

    fun getItems(district: String? = null) {
        viewModelScope.launch {
            if (district.isNullOrBlank())
                getItems()
            else
                _items.value = repository.getNews(district)
        }
    }
}