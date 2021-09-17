package com.gustu.rekomendasi.buku.presenter

import com.gustu.rekomendasi.buku.model.Login
import com.gustu.rekomendasi.buku.model.LoginItem
import com.gustu.rekomendasi.buku.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**Created by Gustu Maulana Firmansyah on 11,September,2021  gustumaulanaf@gmail.com **/
class LoginPresenter(val client: RetrofitClient, val view: LoginView) {
    fun login(userID: Int, password: Int) {
        client.api.login(userID, password).enqueue(object :Callback<List<LoginItem>>{
            override fun onResponse(
                call: Call<List<LoginItem>>,
                response: Response<List<LoginItem>>
            ) {
                if (response.isSuccessful){
                    if (response.code()==200){
                        view.onSuccess(response.body()!!)
                    }else{
                        view.onError("${response.code()}:${response.message()}")
                    }
                }
                else{
                    view.onError("${response.code()}:${response.message()}")

                }
            }

            override fun onFailure(call: Call<List<LoginItem>>, t: Throwable) {
                view.onError("${t}")
            }

        })
    }
}

interface LoginView {
    fun onSuccess(body: List<LoginItem>)
    fun onError(s: String)
}