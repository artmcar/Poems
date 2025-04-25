package com.pirmp.poems.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pirmp.poems.R
import com.pirmp.poems.databinding.FragmentUserPoemBinding

class UserPoemFragment : Fragment() {
    private var _binding: FragmentUserPoemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserPoemBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_userPoemFragment_to_userPoemAddFragment)
        }

        return binding.root
    }


}