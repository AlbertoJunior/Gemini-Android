package albertojunior.setor0.app.establishment.ui.adapter

import albertojunior.setor0.app.establishment.databinding.ItemEstablishmentBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

internal class EstablishmentItemAdapter : ListAdapter<String, EstablishmentItemAdapter.ItemViewHolder>(DiffCallback) {
    private object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
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
        fun bind(item: String) {
            binding.item = item
        }
    }

}