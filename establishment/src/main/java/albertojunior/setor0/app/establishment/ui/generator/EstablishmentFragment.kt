package albertojunior.setor0.app.establishment.ui.generator

import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits
import albertojunior.setor0.app.establishment.databinding.FragmentEstablishmentBinding
import albertojunior.setor0.app.establishment.ui.adapter.EstablishmentItemAdapter
import albertojunior.setor0.app.establishment.ui.adapter.EstablishmentTraitItemAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EstablishmentFragment : Fragment() {
    private lateinit var binding: FragmentEstablishmentBinding
    private val viewModel: EstablishmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEstablishmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewmodel = viewModel
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
        binding.btGenerate.setOnClickListener {
            binding.btGenerate.isEnabled = false
            viewModel.generate(binding.itSize.text.toString(), binding.itDistrict.text.toString())
        }

        binding.btCopy.setOnClickListener {
            viewModel.shareNews(requireContext())
        }

        binding.btBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupAdapters() {
        setupCharacteristicAdapter(binding.rvCharacteristics, viewModel.characteristics)
        setupTraitAdapter(binding.rvGoodTraits, viewModel.goodTraits)
        setupTraitAdapter(binding.rvBadTraits, viewModel.badTraits)
    }

    private fun setupCharacteristicAdapter(
        recycler: RecyclerView,
        data: LiveData<List<String>>
    ) {
        val establishmentItemAdapter = EstablishmentItemAdapter()
        recycler.adapter = establishmentItemAdapter
        data.observe(viewLifecycleOwner) {
            establishmentItemAdapter.submitList(it)
            binding.btGenerate.isEnabled = true
        }
    }

    private fun setupTraitAdapter(
        recycler: RecyclerView,
        data: LiveData<List<EstablishmentTraits>>
    ) {
        EstablishmentTraitItemAdapter().apply {
            recycler.adapter = this
            data.observe(viewLifecycleOwner) {
                submitList(it)
                binding.btGenerate.isEnabled = true
            }
        }
    }
}
