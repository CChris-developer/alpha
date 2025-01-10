package com.example.myapplication.dagger

import com.example.myapplication.mvvm.CardRepository
import com.example.myapplication.utils.RetrofitCard
import com.example.myapplication.db.CardDao
import dagger.Module
import dagger.Provides

@Module

class AppModule {
    @Provides
    fun provideCardRepository(retrofit: RetrofitCard, cardDao: CardDao): CardRepository =
        CardRepository(retrofit, cardDao)
}