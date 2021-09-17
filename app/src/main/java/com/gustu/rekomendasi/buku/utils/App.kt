package com.gustu.rekomendasi.buku.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        @get:Synchronized
        var context: Context? = null
            private set
    }
}
