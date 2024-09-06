package albertojunior.setor0.app.ui.news

import albertojunior.setor0.app.databinding.FragmentNewsBinding
import albertojunior.setor0.app.utils.ContextUtils
import albertojunior.setor0.app.utils.EventObserver
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentNewsBinding.inflate(inflater, container, false)
            .also {
                binding = it
                it.viewModel = viewModel
                it.lifecycleOwner = viewLifecycleOwner
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configFadeIn()
        configFadeOut()
    }

    private fun configFadeIn() {
        val fadeIn = ContextUtils.generateDefaultFadeIn(requireContext())
        viewModel.fadeIn.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                binding.newsContainer.startAnimation(fadeIn)
            }
        })
    }

    private fun configFadeOut() {
        val fadeOut = ContextUtils.generateDefaultFadeOut(requireContext())
        viewModel.fadeOut.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                binding.newsContainer.startAnimation(fadeOut)
            }
        })
    }
}