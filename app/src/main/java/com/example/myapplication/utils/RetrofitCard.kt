package com.example.myapplication.utils

import com.example.myapplication.models.CardInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitCard {

    @GET("{id}")
    suspend fun getCard(@Path("id") id: Int): Response<CardInfo>
}