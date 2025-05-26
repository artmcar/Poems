package com.pirmp.poems.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pirmp.poems.R
import com.pirmp.poems.databinding.FragmentUserPoemBinding
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
        val adapter = UserPoemAdapter()
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


}