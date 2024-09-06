package albertojunior.setor0.app.noticias.ui.features

import albertojunior.setor0.app.noticias.databinding.FragmentFeaturesBinding
import albertojunior.setor0.app.noticias.ui.features.adapter.FeaturesAdapter
import albertojunior.setor0.app.noticias.utils.EventObserver
import albertojunior.setor0.app.noticias.utils.extension.hideNavBar
import albertojunior.setor0.app.noticias.utils.extension.showNavBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeaturesFragment : Fragment() {
    private lateinit var binding: FragmentFeaturesBinding
    private val viewModel: FeaturesViewModel by viewModels()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFeaturesBinding.inflate(inflater, container, false)
            .also {
                it.lifecycleOwner = this
                binding = it
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
    }

    private fun setupAdapter() {
        val adapter = FeaturesAdapter { viewModel.onFeatureClick(it) }

        binding.rvFeature.adapter = adapter
        viewModel.features.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupObservers() {
        viewModel.featureClicked.observe(
            viewLifecycleOwner,
            EventObserver { navController.navigate(it.id) }
        )

        viewModel.hideNavBar.observe(viewLifecycleOwner, EventObserver {
            if (it) hideNavBar()
        })

        viewModel.fetchFeatures()
    }

    override fun onResume() {
        super.onResume()
        showNavBar()
    }
}