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
import com.pirmp.poems.R
import com.pirmp.poems.databinding.FragmentAssetsPoemBinding
import com.pirmp.poems.db.assetspoems.AssetsDbFields
import com.pirmp.poems.db.assetspoems.AssetsViewModel

class AssetsPoemFragment : Fragment() {
    private var _binding: FragmentAssetsPoemBinding? = null
    private val binding get() = _binding!!
    private lateinit var assetsViewModel: AssetsViewModel
    private lateinit var adapter: AssetsPoemAdapter

/*    private var selectedPoem = 0
    private var selectedPoet = 0
    private var selectedFav = 0*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssetsPoemBinding.inflate(inflater, container, false)

        //RecyclerView
        adapter = AssetsPoemAdapter(onFavClicked = { poem -> toggleFav(poem) })
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*        binding.toolbarAssetsPoem.inflateMenu(R.menu.assets_poem_menu)
        binding.toolbarAssetsPoem.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_sort_poem_by_alpha -> {
                    if (selectedPoem == 0) {
                        getPoemsByPoem()
                        selectedPoem = 1
                    } else {
                        getPoemsByBackPoem()
                        selectedPoem = 0
                    }

                    true
                }
                R.id.action_sort_poet_by_alpha -> {
                    if (selectedPoet == 0) {
                        getPoemsByPoetName()
                        selectedPoet = 1
                    } else {
                        getPoemsByBackPoetName()
                        selectedPoet = 0
                    }
                    true
                }
                R.id.action_sort_poem_by_fav -> {
                    if (selectedFav == 0) {
                        getPoemsByFav()
                        selectedFav = 1
                    } else {
                        getPoemsByBackFav()
                        selectedFav = 0
                    }
                    true
                }
                R.id.action_sort_poem_by_date_old -> {
                    getPoemsByDateOld()
                    true
                }
                R.id.action_sort_poem_by_date_new -> {
                    getPoemsByDateNew()
                    true
                }
                else -> false
            }
        }*/
    }

    private fun getPoemsByBackPoetName() {
        assetsViewModel.getPoemsByBackPoetName.observe(viewLifecycleOwner) { sorted ->
            adapter.setData(sorted)
        }
    }

    private fun getPoemsByBackPoem() {
        assetsViewModel.getPoemsByBackPoem.observe(viewLifecycleOwner) { sorted ->
            adapter.setData(sorted)
        }
    }

    private fun getPoemsByBackFav() {
        assetsViewModel.getPoemsByBackFav.observe(viewLifecycleOwner) { sorted ->
            adapter.setData(sorted)
        }
    }

    private fun getPoemsByDateNew() {
        assetsViewModel.getPoemsByDateNew.observe(viewLifecycleOwner) { sorted ->
            adapter.setData(sorted)
        }
    }

    private fun getPoemsByDateOld() {
        assetsViewModel.getPoemsByDateOld.observe(viewLifecycleOwner) { sorted ->
            adapter.setData(sorted)
        }
    }

    private fun getPoemsByFav() {
        assetsViewModel.getPoemsByFav.observe(viewLifecycleOwner) { sorted ->
            adapter.setData(sorted)
        }
    }

    private fun getPoemsByPoetName() {
        assetsViewModel.getPoemsByPoetName.observe(viewLifecycleOwner) { sorted ->
            adapter.setData(sorted)
        }
    }

    private fun getPoemsByPoem() {
        assetsViewModel.getPoemsByPoem.observe(viewLifecycleOwner) { sorted ->
            adapter.setData(sorted)
        }
    }

    private fun toggleFav(fields: AssetsDbFields) {
        val newFavValue = !fields.fav
        val updatedField = fields.copy(fav = newFavValue)
        assetsViewModel.updateField(updatedField)
        Toast.makeText(requireContext(), R.string.fav_updated, Toast.LENGTH_SHORT).show()
    }

}