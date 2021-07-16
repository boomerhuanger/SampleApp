package com.example.sampleapp.Fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.helloworld.UserFragment
import com.example.sampleapp.R

class MainActivity : AppCompatActivity() {
    private var fragment: Fragment? = null
    private val userFragment: String = "User Fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "onCreate: Started.")
        val fm = supportFragmentManager
        fragment = fm.findFragmentByTag(userFragment)
        if (fragment == null) {
            val ft = fm.beginTransaction()
            fragment = UserFragment(this)
            ft.add(R.id.container, fragment as UserFragment, userFragment)
            ft.commit()
        }
    }
}