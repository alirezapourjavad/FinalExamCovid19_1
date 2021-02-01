package com.example.finalexamcovid19.network

import com.example.finalexamcovid19.dbModel.Summary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("summary")
    fun getSummaryList(): Call<Summary>

}