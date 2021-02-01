package com.example.finalexamcovid19.home

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.finalexamcovid19.dbModel.Country
import com.example.finalexamcovid19.dbModel.Summary
import com.example.finalexamcovid19.network.ApiClient
import com.example.finalexamcovid19.network.ApiRepository
import com.example.finalexamcovid19.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(appContext: Application) : AndroidViewModel(appContext) {
    var response: Summary? = null
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

//                 countryList = response.body()?.countries!!
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
//
//    fun setSearchAdapter(wordList :MutableList<Country>){
//        adapter.submitList(wordList)
//    }
//
//    fun  sortByDeaths (list:List<Country>){
//         adapter.submitList(list)
//    }
//
//    fun sortByCountries (list:List<Country>){
//        adapter.submitList(list)
//    }
//
//    fun sortByCase (list:List<Country>){
//        adapter.submitList(list)
//    }

}


