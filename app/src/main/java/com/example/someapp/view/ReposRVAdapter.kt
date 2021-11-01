package com.example.someapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.someapp.databinding.ItemRepoBinding

class ReposRVAdapter(
    private val presenter: IRepoListPresenter
) : Adapter<ReposRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRepoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)).apply {
                itemView.setOnClickListener {presenter.itemClickListener?.invoke(this)}
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })


    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root), IRepoItemView {
        override var pos = -1
        override fun setName(name: String) {
            binding.tvRepoName.text = name
        }
    }
}