package albertojunior.setor0.app.noticias.ui.dashboard

import albertojunior.setor0.app.noticias.databinding.FragmentDashboardBinding
import albertojunior.setor0.app.noticias.model.news.News
import albertojunior.setor0.app.noticias.ui.dashboard.adapter.NewsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDashboardBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NewsAdapter { item: News ->
            viewModel.onItemClicked(item)
        }
        binding.rvItemAdapter.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.itDistrict.onItemClickListener = AdapterView.OnItemClickListener { _, view, _, _ ->
            viewModel.getItems((view as? MaterialTextView)?.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getItems(binding.itDistrict.text.toString())
    }
}