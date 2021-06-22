package com.example.settingsfragment


import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupActionBarWithNavController(findNavController(R.id.main_fragment))




    }


    override fun onSupportNavigateUp(): Boolean {
        val navController1 : NavController = findNavController(R.id.main_fragment)

        return navController1.navigateUp() || super.onSupportNavigateUp()
    }




}