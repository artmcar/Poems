package com.pirmp.poems.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pirmp.poems.R
import com.pirmp.poems.databinding.FragmentUserPoemAddBinding
import com.pirmp.poems.db.userpoems.DbFields
import com.pirmp.poems.db.userpoems.UserViewModel


class UserPoemAddFragment : Fragment() {
    private var _binding: FragmentUserPoemAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserPoemAddBinding.inflate(inflater, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

       /* binding.addButton.setOnClickListener {
            insertDataToDatabase()
        }*/
        /*binding.backButton.setOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarUserPoemAdd.inflateMenu(R.menu.add_poem_menu)
        binding.toolbarUserPoemAdd.setNavigationIcon(R.drawable.ic_back_arrow)
        binding.toolbarUserPoemAdd.setNavigationOnClickListener { view ->
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.toolbarUserPoemAdd.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_save_poem -> {
                    insertDataToDatabase()
                    true
                }
                /*R.id.action_back -> {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                    true
                }*/
                else -> false
            }
        }
    }

    private fun insertDataToDatabase() {
        val author = binding.editTextAuthor.text.toString()
        val poem = binding.editTextPoem.text.toString()
        val content = binding.editTextContent.text.toString()
        val date = binding.editTextDate.text.toString()
        val place = binding.editTextPlace.text.toString()


        if(inputCheck(author, poem, content, date, place)){
            //Создаем объект класса DbFields
            val userPoem = DbFields(0,author, poem, content, date, place, false)
            //Добавляем стих в БД
            userViewModel.insertPoem(userPoem)
            Toast.makeText(requireContext(), "Poem successfully added!", Toast.LENGTH_LONG).show()
            //Возвращаемся назад
            findNavController().navigate(R.id.action_userPoemAddFragment_to_userPoemFragment)
        }else{
            Toast.makeText(requireContext(), "Smth went wrong! Probably all fields are not filled in", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(author: String, poem: String, content: String, date: String, place: String): Boolean{
        return !(TextUtils.isEmpty(author) || TextUtils.isEmpty(poem) || TextUtils.isEmpty(content) || TextUtils.isEmpty(date) || TextUtils.isEmpty(place))
    }


}