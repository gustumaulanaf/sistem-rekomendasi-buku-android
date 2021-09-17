package com.gustu.rekomendasi.buku.retrofit

import com.gustu.rekomendasi.buku.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**Created by Gustu Maulana Firmansyah on 02,September,2021  gustumaulanaf@gmail.com **/
interface API {
    @GET("/books")
    fun getBooks(
        @Query("page") page: Int
    ): Call<Books>

    @GET("/cari_buku")
    fun searchBook(
        @Query("book_name") bookName: String,
        @Query("negara") negara: String
    ): Call<List<BooksItem>>

    @GET("/buku_terbaru")
    fun getBukuBaru(
        @Query("negara") negara: String
    ): Call<List<BooksItem>>

    @GET("/rekomendasi_negara")
    fun getRekomendasiNegara(
        @Query("page") page: Int,
        @Query("negara") negara: String
    ): Call<List<BooksItem>>

    @GET("/rekomendasi")
    fun getRekomendasi(
        @Query("userID") userID: String,
        @Query("negara") negara: String,
        @Query("bookID") bookID: String
    ): Call<Rekomendasi>

    @GET("/login")
    fun login(
        @Query("user_id") userID: Int,
        @Query("password") password: Int
    ): Call<List<LoginItem>>
}