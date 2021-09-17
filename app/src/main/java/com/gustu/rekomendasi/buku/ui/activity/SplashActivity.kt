package com.gustu.rekomendasi.buku.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.gustu.rekomendasi.buku.R
import com.gustu.rekomendasi.buku.utils.SharedPrefUtil

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(object : Runnable {
            override fun run() {
                if (SharedPrefUtil.getBoolean("isLogin")) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
            }

        }, 3000)
    }
}