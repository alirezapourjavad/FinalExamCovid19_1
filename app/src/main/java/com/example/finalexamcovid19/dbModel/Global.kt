package com.example.finalexamcovid19.dbModel


import com.google.gson.annotations.SerializedName

data class Global(
    @SerializedName("ID")
    val iD: String?,
    @SerializedName("NewConfirmed")
    val newConfirmed: Int?,
    @SerializedName("NewDeaths")
    val newDeaths: Int?,
    @SerializedName("NewRecovered")
    val newRecovered: Int?,
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int?,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int?,
    @SerializedName("TotalRecovered")
    val totalRecovered: Int?
)