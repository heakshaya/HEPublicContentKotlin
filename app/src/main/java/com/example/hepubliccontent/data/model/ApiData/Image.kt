package com.example.hepubliccontent.data.model.ApiData


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("aspectRatio")
    val aspectRatio: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("originalUrl")
    val originalUrl: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("type")
    val type: Int
)