package com.example.navapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.navapp.databinding.FragmentHomeBinding
import com.example.navapp.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_second,container,false)

        val input = requireArguments().getString("name")
        binding.enteredText.text = input.toString()

        return binding.root
    }

}