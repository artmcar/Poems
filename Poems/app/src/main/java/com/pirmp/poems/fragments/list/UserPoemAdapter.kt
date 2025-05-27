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
import com.pirmp.poems.databinding.PoemItemBinding
import com.pirmp.poems.db.userpoems.DbFields

class UserPoemAdapter: RecyclerView.Adapter<UserPoemAdapter.PoemViewHolder>() {
    private var poemList = emptyList<DbFields>()

    class PoemViewHolder(private val binding : PoemItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(currentItem: DbFields){
            binding.poemTv.text = currentItem.poem
            binding.nameTv.text = currentItem.author

            val fav_btn_tint_color = if(currentItem.fav == true) {
                ContextCompat.getColor(binding.root.context, R.color.fav_red)
            } else {
                ContextCompat.getColor(binding.root.context, R.color.fav_gray)
            }
            binding.favButton.imageTintList = ColorStateList.valueOf(fav_btn_tint_color)



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemViewHolder {
        val binding = PoemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PoemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PoemViewHolder, position: Int) {
        val currentItem = poemList[position]
        holder.itemView.setOnClickListener {
            Log.d("RRR","id=${poemList[position].id}")
            val bundle = Bundle()
            bundle.putInt("user_param", poemList[position].id)
            holder.itemView.findNavController().navigate(R.id.action_userPoemFragment_to_readFragment, bundle)
        }
        holder.bind(currentItem)

    }

    override fun getItemCount(): Int {
        return poemList.size
    }

    fun setData(poem: List<DbFields>){
        this.poemList = poem
        notifyDataSetChanged()
    }

}