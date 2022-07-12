package com.example.hepubliccontent.data.model.ApiData


import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("page")
    val page: Page
)