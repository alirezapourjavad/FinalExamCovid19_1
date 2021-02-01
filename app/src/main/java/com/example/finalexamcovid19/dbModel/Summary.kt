package com.example.finalexamcovid19.dbModel


import com.google.gson.annotations.SerializedName

data class Summary(
    @SerializedName("Countries")
    val countries: List<Country>?,
    @SerializedName("Date")
    val date: String?,
    @SerializedName("Global")
    val global: Global?,
    @SerializedName("ID")
    val iD: String?,
    @SerializedName("Message")
    val message: String?
)