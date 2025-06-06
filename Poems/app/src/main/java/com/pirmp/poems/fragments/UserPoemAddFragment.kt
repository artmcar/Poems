package com.pirmp.poems.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
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
import java.util.Calendar


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

        //Мотивационный текст
        setMotivationText()

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
                else -> false
            }
        }
    }

    private fun setMotivationText(){
        val motivation_prefs = requireContext().getSharedPreferences("motivation_prefs", Context.MODE_PRIVATE)
        val last_motivation = motivation_prefs.getInt("motivation_prefs", 0)
        val motivations = resources.getStringArray(R.array.motivation)
        val newMotivation : String
        var new_motivation_index = last_motivation + 1
        if (new_motivation_index > motivations.size - 1){
            new_motivation_index = 0
            newMotivation = motivations[new_motivation_index]
        }
        else{
            newMotivation = motivations[new_motivation_index]
        }
        binding.motivationText.movementMethod = ScrollingMovementMethod()
        binding.motivationText.text = newMotivation
        motivation_prefs.edit().putInt("motivation_prefs",new_motivation_index).apply()
    }


    private fun insertDataToDatabase() {
        val author = binding.editTextAuthor.text.toString()
        val poem = binding.editTextPoem.text.toString()
        val content = binding.editTextContent.text.toString()
        var date = binding.editTextDate.text.toString().toIntOrNull()
        var place = binding.editTextPlace.text.toString()

        val calendar = Calendar.getInstance().get(Calendar.YEAR)


        if (date == null){
            if(inputCheck(author, poem, content)){
                if (TextUtils.isEmpty(place)){
                    place = "none"
                }
                date = 9999

                //Создаем объект класса DbFields
                val userPoem = DbFields(0,author, poem, content, date, place, false)
                //Добавляем стих в БД
                userViewModel.insertPoem(userPoem)
                Toast.makeText(requireContext(), R.string.poem_added, Toast.LENGTH_LONG).show()
                //Возвращаемся назад
                findNavController().navigate(R.id.action_userPoemAddFragment_to_userPoemFragment)
            }else{
                Toast.makeText(requireContext(), R.string.fields_not_fill, Toast.LENGTH_LONG).show()
            }
        }
        else{
            if (date > calendar){
                Toast.makeText(requireContext(), R.string.wrong_date, Toast.LENGTH_LONG).show()
            }
            else{
                if(inputCheck(author, poem, content)){
                    if (TextUtils.isEmpty(place)){
                        place = "none"
                    }
                    //Создаем объект класса DbFields
                    val userPoem = DbFields(0,author, poem, content, date, place, false)
                    //Добавляем стих в БД
                    userViewModel.insertPoem(userPoem)
                    Toast.makeText(requireContext(), R.string.poem_added, Toast.LENGTH_LONG).show()
                    //Возвращаемся назад
                    findNavController().navigate(R.id.action_userPoemAddFragment_to_userPoemFragment)
                }else{
                    Toast.makeText(requireContext(), R.string.fields_not_fill, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun inputCheck(author: String, poem: String, content: String): Boolean{
        return !(TextUtils.isEmpty(author) || TextUtils.isEmpty(poem) || TextUtils.isEmpty(content))
    }


}