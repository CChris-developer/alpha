package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class CardInfo(
@SerializedName("number") val number: NumberInfo,
@SerializedName("scheme") val scheme: String,
@SerializedName("type") val type: String,
@SerializedName("brand") val brand: String,
@SerializedName("prepaid") val prepaid: Boolean,
@SerializedName("country") val country: CountryInfo,
@SerializedName("bank") val bank: BankInfo
)