package com.example.hepubliccontent.data.model.ApiData


import com.google.gson.annotations.SerializedName

data class ContentCategory(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("uniqueId")
    val uniqueId: String
)