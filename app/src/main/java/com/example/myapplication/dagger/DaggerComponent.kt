package com.example.myapplication.dagger

import android.content.Context
import com.example.myapplication.mvvm.CardRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class, DataBaseModule::class, AppModule::class])
interface DaggerComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DaggerComponent
    }

    fun getCardRepository(): CardRepository
}