package com.example.hepubliccontent.data.model.ApiStyle


import com.google.gson.annotations.SerializedName

data class Logo(
    @SerializedName("android")
    val android: String,
    @SerializedName("ios")
    val ios: String,
    @SerializedName("webOrAdmin")
    val webOrAdmin: String
)