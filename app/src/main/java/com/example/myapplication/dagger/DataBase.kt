package com.example.myapplication.dagger

import android.content.Context
import androidx.room.Room
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.CardDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "db"
        )
            .build()
    }

    @Provides
    fun provideActionsDao(database: AppDatabase): CardDao {
        return database.cardDao()
    }
}