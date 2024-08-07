package albertojunior.setor0.app.establishment.ui

import albertojunior.setor0.app.establishment.databinding.FragmentEstablishmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class EstablishmentFragment : Fragment() {
    private lateinit var binding: FragmentEstablishmentBinding
    private val viewModel by ViewModelDelegate<EstablishmentViewModel>(this)

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
        setupAdapter(binding.rvCharacteristics, viewModel.characteristics)
        setupAdapter(binding.rvGoodTraits, viewModel.goodTraits)
        setupAdapter(binding.rvBadTraits, viewModel.badTraits)
    }

    private fun setupAdapter(recycler: RecyclerView, data: LiveData<List<String>>) {
        val establishmentItemAdapter = EstablishmentItemAdapter()
        recycler.adapter = establishmentItemAdapter
        data.observe(viewLifecycleOwner) {
            establishmentItemAdapter.submitList(it)
            binding.btGenerate.isEnabled = true
        }
    }
}