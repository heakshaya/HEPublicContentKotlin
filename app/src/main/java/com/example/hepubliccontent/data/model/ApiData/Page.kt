package com.example.hepubliccontent.data.model.ApiData


import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: Boolean,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("totalRecords")
    val totalRecords: Int
)