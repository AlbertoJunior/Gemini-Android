package albertojunior.setor0.app.ui.features.adapter

import albertojunior.setor0.app.data.model.features.FeatureView
import albertojunior.setor0.app.databinding.ItemFeatureBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FeaturesAdapter(
    private val onItemClick: (FeatureView) -> Unit
) : ListAdapter<FeatureView, FeaturesAdapter.FeaturesViewHolder>(DiffCallback) {
    private object DiffCallback : DiffUtil.ItemCallback<FeatureView>() {
        override fun areItemsTheSame(oldItem: FeatureView, newItem: FeatureView) = oldItem == newItem

        override fun areContentsTheSame(oldItem: FeatureView, newItem: FeatureView) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesViewHolder {
        return FeaturesViewHolder(
            ItemFeatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FeaturesViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClick)
    }

    class FeaturesViewHolder(private val binding: ItemFeatureBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FeatureView, onItemClick: (FeatureView) -> Unit) {
            binding.item =  item
            binding.root.setOnClickListener { onItemClick.invoke(item) }
        }
    }

}