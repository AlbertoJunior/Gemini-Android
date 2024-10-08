package albertojunior.setor0.app.ui.home

import albertojunior.setor0.app.databinding.FragmentHomeBinding
import albertojunior.setor0.app.utils.ContextUtils
import albertojunior.setor0.app.utils.extension.showNavBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater, container, false).also {
            binding = it
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.generateWelcomeText()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.welcomeText.observe(viewLifecycleOwner) {
            val fadeIn = ContextUtils.generateDefaultFadeIn(requireContext())
            binding.textWelcome.startAnimation(fadeIn)
            binding.clWelcomeMessage.startAnimation(fadeIn)
        }
    }

    override fun onResume() {
        super.onResume()
        showNavBar()
    }

}