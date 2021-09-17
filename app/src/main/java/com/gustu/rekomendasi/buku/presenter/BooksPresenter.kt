package com.gustu.rekomendasi.buku.presenter

import com.gustu.rekomendasi.buku.model.Books
import com.gustu.rekomendasi.buku.model.BooksItem
import com.gustu.rekomendasi.buku.model.Rekomendasi
import com.gustu.rekomendasi.buku.model.RekomendasiItem
import com.gustu.rekomendasi.buku.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**Created by Gustu Maulana Firmansyah on 11,September,2021  gustumaulanaf@gmail.com **/
class BooksPresenter(val client: RetrofitClient, val view: BookView) {
    fun getBook(page: Int) {
        client.api.getBooks(page).enqueue(object : Callback<Books> {
            override fun onResponse(
                call: Call<Books>,
                response: Response<Books>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        view.onSuccess(response.body()!!)
                    } else {
                        view.onError("${response.code()}: ${response.message()}")
                    }
                } else {
                    view.onError("${response.code()}: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Books>, t: Throwable) {
                view.onError("${t}")
            }

        })
    }

    fun searchBook(bookName: String,negara:String) {
        client.api.searchBook(bookName,negara).enqueue(object : Callback<List<BooksItem>> {
            override fun onResponse(
                call: Call<List<BooksItem>>,
                response: Response<List<BooksItem>>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        view.onSuccess2(response.body()!!)
                    } else {
                        view.onError("${response.code()}: ${response.message()}")
                    }
                } else {
                    view.onError("${response.code()}: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<BooksItem>>, t: Throwable) {
                view.onError("${t}")
            }

        })
    }

    fun getBukuBaru(negara:String) {
        client.api.getBukuBaru(negara).enqueue(object : Callback<List<BooksItem>> {
            override fun onResponse(
                call: Call<List<BooksItem>>,
                response: Response<List<BooksItem>>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        view.onSuccess2(response.body()!!)
                    } else {
                        view.onError("${response.code()}: ${response.message()}")
                    }
                } else {
                    view.onError("${response.code()}: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<BooksItem>>, t: Throwable) {
                view.onError("${t}")
            }

        })
    }

    fun rekomendasiNegara(page: Int, negara: String) {
        client.api.getRekomendasiNegara(page,negara).enqueue(object : Callback<List<BooksItem>> {
            override fun onResponse(
                call: Call<List<BooksItem>>,
                response: Response<List<BooksItem>>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        view.onSuccess2(response.body()!!)
                    } else {
                        view.onError("${response.code()}: ${response.message()}")
                    }
                } else {
                    view.onError("${response.code()}: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<BooksItem>>, t: Throwable) {
                view.onError("${t}")
            }

        })
    }

    fun rekomendasi(userID: String, negara: String, bookID: String) {
        client.api.getRekomendasi(userID, negara, bookID)
            .enqueue(object : Callback<Rekomendasi> {
                override fun onResponse(call: Call<Rekomendasi>, response: Response<Rekomendasi>) {
                    if (response.isSuccessful) {
                        if (response.code() == 200) {
                            view.onSuccess3(response.body()?.rekomendasi!!)
                        } else {
                            view.onError("${response.code()}: ${response.message()}")
                        }
                    } else {
                        view.onError("${response.code()}: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<Rekomendasi>, t: Throwable) {
                    view.onError("${t}")
                }

            })
    }

}

interface BookView {
    fun onSuccess(body: Books)
    fun onError(s: String)
    fun onSuccess2(body: List<BooksItem>)
    fun onSuccess3(body: List<RekomendasiItem>)

}