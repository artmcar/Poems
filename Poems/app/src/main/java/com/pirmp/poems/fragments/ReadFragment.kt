package com.pirmp.poems.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.pirmp.poems.databinding.FragmentReadBinding
import com.pirmp.poems.db.assetspoems.AssetsDatabase
import com.pirmp.poems.db.assetspoems.AssetsDbFields
import com.pirmp.poems.db.userpoems.DbFields
import com.pirmp.poems.db.userpoems.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private const val ARG_PARAM1 = "user_param"
private const val ARG_PARAM2 = "assets_param"

class ReadFragment : Fragment() {
    private var _binding: FragmentReadBinding? = null
    private val binding get() = _binding!!

    private var user_param: Int? = null
    private var assets_param: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user_param = it.getInt(ARG_PARAM1)
            Log.d("RRR", user_param.toString())
            assets_param = it.getInt(ARG_PARAM2)
            Log.d("RRR", assets_param.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReadBinding.inflate(inflater, container, false)

        val user_db = UserDatabase.getDatabase(requireContext())
        val assets_db = AssetsDatabase.getDatabase(requireContext())

        lifecycleScope.launch {
            if(user_param != 0){
                val poem = withContext(Dispatchers.IO) {
                    user_db.userDao().getPoemById(user_param!!)
                }
                showUserPoem(poem)
            } else if(assets_param != 0){
                val poem = withContext(Dispatchers.IO) {
                    assets_db.assetsDao().getPoemById(assets_param!!)
                }
                showAssetsPoem(poem)
            }
        }

        return binding.root
    }

    private fun showUserPoem(poem: DbFields){
        binding.poemTv.text = poem.poem
        binding.authorTv.text = poem.author
        binding.contentTv.text = poem.content
        binding.placeTv.text = if (poem.place == "none") "" else poem.place
        binding.dateTv.text = if (poem.date== "0000") "" else poem.date
    }

    private fun showAssetsPoem(poem: AssetsDbFields){
        binding.poemTv.text = poem.poem
        binding.authorTv.text = poem.author
        binding.contentTv.text = poem.content
        binding.placeTv.text = if (poem.place == "none") "" else poem.place
        binding.dateTv.text = if (poem.date== "0000") "" else poem.date
    }

}