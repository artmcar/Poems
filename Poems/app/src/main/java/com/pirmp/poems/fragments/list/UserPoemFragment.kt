package com.pirmp.poems.fragments.list

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pirmp.poems.R
import com.pirmp.poems.databinding.FragmentUserPoemBinding
import com.pirmp.poems.db.userpoems.DbFields
import com.pirmp.poems.db.userpoems.UserViewModel

class UserPoemFragment : Fragment() {
    private var _binding: FragmentUserPoemBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserPoemBinding.inflate(inflater, container, false)

        //RecyclerView
        val adapter = UserPoemAdapter(
            onDeleteClicked = { poem -> deletePoem(requireContext(), poem) },
            onFavClicked = { poem -> toggleFav(poem) }
        )
        val recyclerView = binding.userPoemRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getAllPoems.observe(viewLifecycleOwner, Observer{ user ->
            adapter.setData(user)
        })

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_userPoemFragment_to_userPoemAddFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarUserPoem.inflateMenu(R.menu.user_poem_menu)
        binding.toolbarUserPoem.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_delete_all_poems -> {
                    deleteAllPoems()
                    true
                }
                else -> false
            }
        }
    }


    private fun deletePoem(context: Context, fields: DbFields) {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("Yes"){_,_->
            userViewModel.deletePoem(fields)
            Toast.makeText(context, "Successfully deleted: ${fields.poem}",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Delete ${fields.poem}?")
        builder.setMessage("Are you sure you want to delete ${fields.poem}?")
        builder.create().show()
    }

    private fun deleteAllPoems() {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("Yes"){_,_->
            userViewModel.deleteAllPoems()
            Toast.makeText(context, "Successfully deleted everything",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

    private fun toggleFav(fields: DbFields) {
        val newFavValue = !fields.fav
        val updatedField = fields.copy(fav = newFavValue)
        userViewModel.updateField(updatedField)
        Toast.makeText(requireContext(), "Favorite updated", Toast.LENGTH_SHORT).show()
    }



}