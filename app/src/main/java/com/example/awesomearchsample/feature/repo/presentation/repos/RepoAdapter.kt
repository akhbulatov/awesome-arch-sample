package com.example.awesomearchsample.feature.repo.presentation.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomearchsample.R
import com.example.awesomearchsample.databinding.ItemRepoBinding
import com.example.awesomearchsample.feature.repo.domain.model.Repo

private typealias OnRepoClickListener = (Repo) -> Unit

class RepoAdapter(
    private val favoritesClickListener: OnRepoClickListener
) : ListAdapter<Repo, RepoAdapter.RepoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemRepoBinding.bind(itemView)

        init {
            with(binding) {
                repoFavoritesImage.setOnClickListener {
                    val item = currentList[bindingAdapterPosition]
                    favoritesClickListener.invoke(item)
                }
            }
        }

        fun bind(item: Repo) {
            with(binding) {
                repoNameLabel.text = item.name
                repoAuthorLabel.text = item.author
                repoDescriptionLabel.text = item.description

                val favoritesIconRes = if (item.inFavorites) {
                    R.drawable.ic_favorite
                } else {
                    R.drawable.ic_favorite_border
                }
                repoFavoritesImage.setImageResource(favoritesIconRes)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }
}