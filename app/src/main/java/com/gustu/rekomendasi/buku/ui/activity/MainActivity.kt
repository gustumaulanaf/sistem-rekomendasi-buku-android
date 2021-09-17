package com.gustu.rekomendasi.buku.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gustu.rekomendasi.buku.R
import com.gustu.rekomendasi.buku.ui.fragment.HomeFragment
import com.gustu.rekomendasi.buku.ui.fragment.ProfileFragment
import com.gustu.rekomendasi.buku.ui.fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gotoFragment(HomeFragment())
        mainNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
//                    title = "Home"
                    gotoFragment(HomeFragment())
                }
                R.id.menu_search -> {
//                    title = "Profile"
                    gotoFragment(SearchFragment())
                } R.id.menu_profile -> {
//                    title = "Profile"
                    gotoFragment(ProfileFragment())
                }
                else -> false
            }
        }
    }

    fun gotoFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.frameMain, fragment).commit()
        return true
    }
}