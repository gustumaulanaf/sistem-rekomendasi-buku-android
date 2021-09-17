package com.gustu.rekomendasi.buku.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Books(

	@field:SerializedName("books")
	val books: List<BooksItem?>? = null,

	@field:SerializedName("total_page")
	val totalPage: Int? = null
)
@Parcelize
data class BooksItem(

	@field:SerializedName("book_title")
	val bookTitle: String? = null,

	@field:SerializedName("isbn")
	val isbn: String? = null,

	@field:SerializedName("year_of_publication")
	val yearPublication: String? = null,

	@field:SerializedName("publisher")
	val publisher: String? = null,

	@field:SerializedName("book_author")
	val bookAuthor: String? = null,

	@field:SerializedName("image_url_s")
	val imageS: String? = null,

	@field:SerializedName("image_url_m")
	val imageM: String? = null,

	@field:SerializedName("image_url_l")
	val imageL: String? = null
):Parcelable
