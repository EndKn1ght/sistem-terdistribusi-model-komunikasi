package com.sister.sampleapp.data

import retrofit2.http.GET

interface ApiService {

    @GET("/quote")
    suspend fun getQuote(): Response
}