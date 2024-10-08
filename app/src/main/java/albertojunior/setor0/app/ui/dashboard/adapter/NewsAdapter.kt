package albertojunior.setor0.app.ui.dashboard.adapter

import albertojunior.setor0.app.data.model.news.News
import albertojunior.setor0.app.databinding.ItemNewsBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(
    private val onItemClick: (News) -> Unit
) : ListAdapter<News, NewsAdapter.NewsViewHolder>(DiffCallback) {
    private object DiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News) = oldItem == newItem

        override fun areContentsTheSame(oldItem: News, newItem: News) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClick)
    }

    class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News, onItemClick: (News) -> Unit) {
            binding.item = item
            binding.root.setOnClickListener { onItemClick.invoke(item) }
        }
    }

}