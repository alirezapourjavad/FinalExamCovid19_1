package com.example.finalexamcovid19.network

import android.widget.TextView
import com.example.finalexamcovid19.dbModel.Summary
import retrofit2.Call

class ApiRepository(val apiService: ApiService) {
    fun summaryResult(): Call<Summary> {
        return apiService.getSummaryList()
    }
}
