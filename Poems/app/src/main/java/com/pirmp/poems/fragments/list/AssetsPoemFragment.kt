package com.pirmp.poems.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pirmp.poems.databinding.FragmentAssetsPoemBinding
import com.pirmp.poems.db.assetspoems.AssetsDbFields
import com.pirmp.poems.db.assetspoems.AssetsViewModel

class AssetsPoemFragment : Fragment() {
    private var _binding: FragmentAssetsPoemBinding? = null
    private val binding get() = _binding!!
    private lateinit var assetsViewModel: AssetsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAssetsPoemBinding.inflate(inflater, container, false)


        //RecyclerView
        val adapter = AssetsPoemAdapter(onFavClicked = { poem -> toggleFav(poem) })
        val recyclerView = binding.assetsPoemRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //AssetsViewModel
        assetsViewModel = ViewModelProvider(this).get(AssetsViewModel::class.java)
        assetsViewModel.getAllPoems.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
        return binding.root
    }

    private fun toggleFav(fields: AssetsDbFields) {
        val newFavValue = !fields.fav
        val updatedField = fields.copy(fav = newFavValue)
        assetsViewModel.updateField(updatedField)
        Toast.makeText(requireContext(), "Favorite updated", Toast.LENGTH_SHORT).show()
    }

}