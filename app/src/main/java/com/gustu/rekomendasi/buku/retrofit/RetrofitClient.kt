package com.gustu.rekomendasi.buku.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**Created by Gustu Maulana Firmansyah on 02,September,2021  gustumaulanaf@gmail.com **/
class RetrofitClient {
    var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .followRedirects(false)
        .followSslRedirects(false)
        .retryOnConnectionFailure(false)
        .cache(null)
        .build()
    lateinit var retrofit: Retrofit
    val BASE_URL = "http://c08f-182-2-74-185.ngrok.io/"
    public val api: API
        get() {
            retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(API::class.java)
        }

}