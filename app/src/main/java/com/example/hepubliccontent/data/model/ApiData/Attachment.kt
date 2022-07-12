package com.example.hepubliccontent.data.model.ApiData


import com.google.gson.annotations.SerializedName

data class Attachment(
    @SerializedName("id")
    val id: Int,
    @SerializedName("label")
    val label: String,
    @SerializedName("path")
    val path: String
)