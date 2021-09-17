package com.gustu.rekomendasi.buku.model

import com.google.gson.annotations.SerializedName

data class Login(

	@field:SerializedName("Login")
	val login: List<LoginItem?>? = null
)

data class LoginItem(

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("age")
	val age: String? = null
)
