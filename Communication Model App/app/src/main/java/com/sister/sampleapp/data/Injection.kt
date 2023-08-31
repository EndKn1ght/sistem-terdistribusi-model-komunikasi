package com.sister.sampleapp.data

object Injection {

    fun provideRepos(): AppRepository {
        val apiService = ApiConfig.getApiService()
        return AppRepository(apiService)
    }

}