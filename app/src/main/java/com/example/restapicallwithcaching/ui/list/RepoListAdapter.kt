package com.example.restapicallwithcaching.ui.list
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restapicallwithcaching.data.model.RepoItem
import com.example.restapicallwithcaching.databinding.RepoListRowLayoutBinding

class RepoListAdapter() : RecyclerView.Adapter<RepoItemViewHolder>() {

    private val items = ArrayList<RepoItem>()

    fun setItems(items: ArrayList<RepoItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val binding: RepoListRowLayoutBinding = RepoListRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) = holder.bind(items[position])
}

class RepoItemViewHolder(private val itemBinding: RepoListRowLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root){
    fun bind(repoItem: RepoItem) {
        itemBinding.repo = repoItem
        itemBinding.executePendingBindings()
    }

}

