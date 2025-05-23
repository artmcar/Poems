package com.pirmp.poems.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pirmp.poems.databinding.PoemItemBinding
import com.pirmp.poems.db.AssetsDbFields

class AssetsPoemAdapter: RecyclerView.Adapter<AssetsPoemAdapter.PoemViewHolder>() {
    private var poemList = emptyList<AssetsDbFields>()

    class PoemViewHolder(private val binding : PoemItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(currentItem: AssetsDbFields){
            binding.poemTv.text = currentItem.poem
            binding.nameTv.text = currentItem.author
            binding.favTv.text = currentItem.fav.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemViewHolder {
        val binding = PoemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PoemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PoemViewHolder, position: Int) {
        val currentItem = poemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return poemList.size
    }

    fun setData(poem: List<AssetsDbFields>){
        this.poemList = poem
        notifyDataSetChanged()
    }

}