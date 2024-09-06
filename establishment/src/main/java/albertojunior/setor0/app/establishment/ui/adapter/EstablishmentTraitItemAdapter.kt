package albertojunior.setor0.app.establishment.ui.adapter

import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits
import albertojunior.setor0.app.establishment.databinding.ItemEstablishmentBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

internal class EstablishmentTraitItemAdapter :
    ListAdapter<EstablishmentTraits, EstablishmentTraitItemAdapter.ItemViewHolder>(DiffCallback) {

    private object DiffCallback : DiffUtil.ItemCallback<EstablishmentTraits>() {
        override fun areItemsTheSame(
            oldItem: EstablishmentTraits,
            newItem: EstablishmentTraits
        ) = oldItem.generatedId == newItem.generatedId

        override fun areContentsTheSame(
            oldItem: EstablishmentTraits,
            newItem: EstablishmentTraits
        ) = oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            ItemEstablishmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        holder.bind(currentList[position])
    }

    class ItemViewHolder(private val binding: ItemEstablishmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EstablishmentTraits) {
            binding.item = item.name
        }
    }

}