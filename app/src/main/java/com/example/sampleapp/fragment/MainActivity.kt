package com.example.sampleapp.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.sampleapp.R

class MainActivity : AppCompatActivity() {
    private var fragment: Fragment? = null
    private val userFragment: String = "User Fragment"
    //private var binding : ActivityMainBinding? = null;
    private lateinit var navController: NavController;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "onCreate: Started.")

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        navController  = navHostFragment.findNavController()

        /*val fm = supportFragmentManager
        fragment = fm.findFragmentByTag(userFragment)
        if (fragment == null) {
            val ft = fm.beginTransaction()
            fragment = UserFragment(this)
            ft.add(R.id.container, fragment as UserFragment, userFragment)
            ft.commit()
        }*/
    }
}