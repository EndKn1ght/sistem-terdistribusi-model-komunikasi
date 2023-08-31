package com.sister.sampleapp.data

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("quote")
	val quote: String? = null,

	@field:SerializedName("author")
	val author: String? = null
)
