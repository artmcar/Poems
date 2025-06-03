package com.pirmp.poems.fragments.list


import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pirmp.poems.R
import com.pirmp.poems.databinding.AssetsPoemItemBinding
import com.pirmp.poems.db.assetspoems.AssetsDbFields


class AssetsPoemAdapter(private val onFavClicked: ((AssetsDbFields) -> Unit)? = null): RecyclerView.Adapter<AssetsPoemAdapter.PoemViewHolder>() {
    private var poemList = emptyList<AssetsDbFields>()

    class PoemViewHolder(val binding : AssetsPoemItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(currentItem: AssetsDbFields){
            binding.assetsPoemTv.text = currentItem.poem
            binding.assetsNameTv.text = currentItem.author

            val fav_btn_tint_color = if(currentItem.fav == true) {
                ContextCompat.getColor(binding.root.context, R.color.fav_red)
            } else {
                ContextCompat.getColor(binding.root.context, R.color.fav_gray)
            }
            binding.assetsFavButton.imageTintList = ColorStateList.valueOf(fav_btn_tint_color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemViewHolder {
        val binding = AssetsPoemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PoemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PoemViewHolder, position: Int) {
        val currentItem = poemList[position]
        holder.itemView.setOnClickListener {
            Log.d("RRR","id=${poemList[position].id}")
            val bundle = Bundle()
            bundle.putInt("assets_param", poemList[position].id)
            holder.itemView.findNavController().navigate(R.id.action_assetsPoemFragment_to_readFragment, bundle)
        }
        holder.binding.assetsFavButton.setOnClickListener {
            onFavClicked?.invoke(currentItem)
        }

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