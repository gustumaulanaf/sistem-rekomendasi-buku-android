package com.gustu.rekomendasi.buku.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Rekomendasi(

	@field:SerializedName("similarity")
	val similarity: List<SimilarityItem?>? = null,

	@field:SerializedName("rekomendasi")
	val rekomendasi: List<RekomendasiItem>? = null,

	@field:SerializedName("RMSE")
	val rMSE: Double? = null
)
@Parcelize
data class RekomendasiItem(

	@field:SerializedName("book_title")
	val bookTitle: String? = null,

	@field:SerializedName("isbn")
	val isbn: String? = null,

	@field:SerializedName("year_publication")
	val yearPublication: String? = null,

	@field:SerializedName("publisher")
	val publisher: String? = null,

	@field:SerializedName("book_author")
	val bookAuthor: String? = null,

	@field:SerializedName("image_s")
	val imageS: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_m")
	val imageM: String? = null,

	@field:SerializedName("image_l")
	val imageL: String? = null
):Parcelable

data class SimilarityItem(

	@field:SerializedName("Simlarity")
	val simlarity: Double? = null,

	@field:SerializedName("userID")
	val userID: String? = null
)
