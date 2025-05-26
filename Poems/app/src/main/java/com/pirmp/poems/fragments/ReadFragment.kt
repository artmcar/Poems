package com.pirmp.poems.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pirmp.poems.R


private const val ARG_PARAM1 = "user_param"
private const val ARG_PARAM2 = "poem_param"


class ReadFragment : Fragment() {

    private var user_param: Int? = null
    private var poem_param: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user_param = it.getInt(ARG_PARAM1)
            Log.d("RRR", user_param.toString())
            poem_param = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read, container, false)
    }


}