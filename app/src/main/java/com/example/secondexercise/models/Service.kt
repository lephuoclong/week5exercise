package com.example.secondexercise.models

//kotlin
data class Service(
    val Id: Long,
    val Type: Int,
    val Url: String,
    val Title: String?,
    val Text: String?,
    val Avatar: String?,
    val BackgroundColor: String?,
    val RestaurantId: Long?,
    val Description: String?,
)
