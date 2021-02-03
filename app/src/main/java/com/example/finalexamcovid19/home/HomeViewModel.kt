package com.example.finalexamcovid19.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.finalexamcovid19.dbModel.Country
import com.example.finalexamcovid19.dbModel.Global
import com.example.finalexamcovid19.dbModel.Summary
import com.example.finalexamcovid19.network.ApiClient
import com.example.finalexamcovid19.network.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(appContext: Application) : AndroidViewModel(appContext) {
    var response: Summary? = null

    private var _mainData = MutableLiveData<Summary>()
    val mainData: LiveData<Summary>
        get() = _mainData


    //    lateinit var countryList: List<Country>
    private val TAG = "HomeViewModel"

    init {
        callApiRequest()
    }


    fun callApiRequest() {
        val apiService = ApiClient.getApiService()
        val apiRepository = ApiRepository(apiService)
        val call: Call<Summary> = apiRepository.summaryResult()
        call.enqueue(object : Callback<Summary> {
            override fun onResponse(call: Call<Summary>, response: Response<Summary>) {
                this@HomeViewModel.response = response.body()
                Log.i(TAG, "onResponse:  ")
                _mainData.value = response.body()
            }

            override fun onFailure(call: Call<Summary>, t: Throwable) {
                Log.e(TAG, "ERROR")
            }
        })
    }

    fun refresh() {
        callApiRequest()
    }

    fun searchWord(word: String): MutableList<Country> {

        val worldList = mutableListOf<Country>()
        for (country in response?.countries!!) {
            if (country.country?.contains(word, true) == true) {
                worldList.add(country)
            }
        }
        return worldList
    }
}


