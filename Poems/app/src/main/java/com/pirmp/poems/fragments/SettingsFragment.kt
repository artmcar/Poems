package com.pirmp.poems.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import com.pirmp.poems.R
import com.pirmp.poems.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.colorSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val select_colors = when(pos){
                    0 -> R.color.signal_black
                    1 -> R.color.medium_persian_blue
                    2 -> R.color.anthracite_grey
                    3 -> R.color.light_sand
                    4 -> R.color.silver
                    5 -> R.color.very_light_yellow_green
                    6 -> R.color.sea_green_crayola
                    7 -> R.color.creamy_yellow
                    8 -> R.color.honeydew
                    9 -> R.color.just_white
                    else -> R.color.just_white
                }
                binding.previewColor.setBackgroundColor(ContextCompat.getColor(requireContext(), select_colors))
                val settings_prefs = requireContext().getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
                settings_prefs.edit().putInt("background_color",select_colors).apply()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.sizeSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val select_size = when(pos){
                    0 -> 1.0f
                    1 -> 1.5f
                    2 -> 2.0f
                    3 -> 2.5f
                    4 -> 3.0f
                    5 -> 3.5f
                    6 -> 4.0f
                    else -> 1.0f
                }
                val standard_size = 20f
                binding.previewText.textSize = standard_size * select_size
                val settings_prefs = requireContext().getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
                settings_prefs.edit().putFloat("select_size", select_size).apply()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        return binding.root
    }

}