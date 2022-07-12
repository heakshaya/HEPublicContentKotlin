package com.example.hepubliccontent.data.model.ApiStyle


import com.google.gson.annotations.SerializedName

data class AppUi(
    @SerializedName("appHeaderBackgroundColor")
    val appHeaderBackgroundColor: String,
    @SerializedName("appHeaderForegroundColor")
    val appHeaderForegroundColor: String,
    @SerializedName("background")
    val background: Background,
    @SerializedName("buttonBackgroundColor")
    val buttonBackgroundColor: String,
    @SerializedName("buttonForegroundColor")
    val buttonForegroundColor: String,
    @SerializedName("emailBanner")
    val emailBanner: String,
    @SerializedName("logo")
    val logo: Logo,
    @SerializedName("logoBackgroundColor")
    val logoBackgroundColor: String,
    @SerializedName("menuBadgeBackgroundColor")
    val menuBadgeBackgroundColor: String,
    @SerializedName("menuBadgeForegroundColor")
    val menuBadgeForegroundColor: String,
    @SerializedName("menuBarBackgroundColor")
    val menuBarBackgroundColor: String,
    @SerializedName("menuItemBackgroundColor")
    val menuItemBackgroundColor: String,
    @SerializedName("menuItemForegroundColor")
    val menuItemForegroundColor: String,
    @SerializedName("subMenuBackgroundColor")
    val subMenuBackgroundColor: String,
    @SerializedName("subMenuForegroundColor")
    val subMenuForegroundColor: String,
    @SerializedName("tabBarSelectedBackgroundColor")
    val tabBarSelectedBackgroundColor: String,
    @SerializedName("tabBarSelectedForegroundColor")
    val tabBarSelectedForegroundColor: String
)