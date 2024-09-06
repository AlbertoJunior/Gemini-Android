package albertojunior.setor0.app.ui.information

import albertojunior.setor0.app.databinding.FragmentInformationBinding
import albertojunior.setor0.app.utils.extension.hideNavBar
import albertojunior.setor0.app.utils.extension.showNavBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationFragment : Fragment() {
    private val viewModel: InformationViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private val args by navArgs<InformationFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentInformationBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNews(args.news)
        viewModel.back.observe(viewLifecycleOwner) { navController.navigateUp() }
    }

    override fun onResume() {
        super.onResume()
        hideNavBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        showNavBar()
    }

}