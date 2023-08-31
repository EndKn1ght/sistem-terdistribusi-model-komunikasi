package com.sister.sampleapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

class AppRepository(private val apiService: ApiService) {

    fun getQoute(): LiveData<Result<Response>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getQuote()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(
            apiService: ApiService,
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(apiService)
            }.also { instance = it }
    }
}