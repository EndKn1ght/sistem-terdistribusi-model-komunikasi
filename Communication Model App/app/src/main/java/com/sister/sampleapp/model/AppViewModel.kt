package com.sister.sampleapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sister.sampleapp.data.AppRepository
import com.sister.sampleapp.data.Response
import com.sister.sampleapp.data.Result

class AppViewModel(private val repos: AppRepository): ViewModel() {

    fun getQuote(): LiveData<Result<Response>> = repos.getQoute()

}