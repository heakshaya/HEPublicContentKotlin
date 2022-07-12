package com.example.hepubliccontent.data.model.ApiStyle


import com.google.gson.annotations.SerializedName

data class Background(
    @SerializedName("hdpi")
    val hdpi: String,
    @SerializedName("ios")
    val ios: String,
    @SerializedName("ipadLandscape")
    val ipadLandscape: String,
    @SerializedName("ipadPortrait")
    val ipadPortrait: String,
    @SerializedName("xhdpi")
    val xhdpi: String,
    @SerializedName("xxhdpi")
    val xxhdpi: String,
    @SerializedName("xxxhdpi")
    val xxxhdpi: String
)