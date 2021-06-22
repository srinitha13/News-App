package com.example.settingsfragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.fragment_filter.*
import kotlinx.android.synthetic.main.fragment_filter.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class FilterFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(com.example.settingsfragment.R.layout.fragment_filter, container, false)



        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        button_health.setOnClickListener {
            val cCategory = button_health.text.toString().toLowerCase()
            val action = FilterFragmentDirections.actionFilterFragmentToHomeFragment(cCategory)
            Navigation.findNavController(view).navigate(action)

        }
        button_health.setOnClickListener {
            val cCategory = button_health.text.toString().toLowerCase()
            val action = FilterFragmentDirections.actionFilterFragmentToHomeFragment(cCategory)
            Navigation.findNavController(view).navigate(action)

        }
        button_general.setOnClickListener {
            val cCategory = button_general.text.toString().toLowerCase()
            val action = FilterFragmentDirections.actionFilterFragmentToHomeFragment(cCategory)
            Navigation.findNavController(view).navigate(action)

        }
        button_business.setOnClickListener {
            val cCategory = button_business.text.toString().toLowerCase()
            val action = FilterFragmentDirections.actionFilterFragmentToHomeFragment(cCategory) //sending data to homefragment
            Navigation.findNavController(view).navigate(action)

        }
        button_science.setOnClickListener {
            val cCategory = button_science.text.toString().toLowerCase()
            val action = FilterFragmentDirections.actionFilterFragmentToHomeFragment(cCategory)
            Navigation.findNavController(view).navigate(action)

        }
        button_sports.setOnClickListener {
            val cCategory = button_sports.text.toString().toLowerCase()
            val action = FilterFragmentDirections.actionFilterFragmentToHomeFragment(cCategory)
            Navigation.findNavController(view).navigate(action)

        }
    }


    }




