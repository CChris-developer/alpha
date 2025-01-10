package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class NumberInfo(
    @SerializedName("length") val length: Int,
    @SerializedName("luhn") val luhn: Boolean
)