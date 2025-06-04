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
import androidx.navigation.fragment.navArgs
import com.pirmp.poems.R
import com.pirmp.poems.databinding.FragmentUpdateBinding
import com.pirmp.poems.db.userpoems.DbFields
import com.pirmp.poems.db.userpoems.UserViewModel
import java.util.Calendar


class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.updateEditTextAuthor.setText(args.currentDbFields.author)
        binding.updateEditTextPoem.setText(args.currentDbFields.poem)
        if(args.currentDbFields.date == 9999){
            binding.updateEditTextDate.setText("")
        }
        else{
            binding.updateEditTextDate.setText(args.currentDbFields.date.toString())
        }
        if (args.currentDbFields.place == "none"){
            binding.updateEditTextPlace.setText("")
        }
        else{
            binding.updateEditTextPlace.setText(args.currentDbFields.place)
        }
        binding.updateEditTextContent.setText(args.currentDbFields.content)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarUpdate.inflateMenu(R.menu.add_poem_menu)
        binding.toolbarUpdate.setNavigationIcon(R.drawable.ic_back_arrow)
        binding.toolbarUpdate.setNavigationOnClickListener { view ->
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.toolbarUpdate.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_save_poem -> {
                    updateItem()
                    true
                }
                else -> false
            }
        }
    }

    private fun updateItem(){
        val author = binding.updateEditTextAuthor.text.toString()
        val poem = binding.updateEditTextPoem.text.toString()
        val content = binding.updateEditTextContent.text.toString()
        var date = binding.updateEditTextDate.text.toString().toIntOrNull()
        var place = binding.updateEditTextPlace.text.toString()

        val calendar = Calendar.getInstance().get(Calendar.YEAR)



        if (date == null){
            if(inputCheck(author, poem, content)){
                if (TextUtils.isEmpty(place)){
                    place = "none"
                }
                date = 9999
                val updatedFields = DbFields(args.currentDbFields.id, author, poem, content, date, place, false)
                mUserViewModel.updateField(updatedFields)
                Toast.makeText(requireContext(), R.string.successfully_updated, Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateFragment_to_userPoemFragment)
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
                    val updatedFields = DbFields(args.currentDbFields.id, author, poem, content, date, place,  args.currentDbFields.fav)
                    mUserViewModel.updateField(updatedFields)
                    Toast.makeText(requireContext(), R.string.successfully_updated, Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_updateFragment_to_userPoemFragment)
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