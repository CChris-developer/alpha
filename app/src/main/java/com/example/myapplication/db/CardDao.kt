package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.models.Card

@Dao
interface CardDao {
    @Query("SELECT * FROM cards where number = :number")
    fun getCard(number: Int): Card

    @Query("SELECT * FROM cards")
    fun getAllCard(): List<Card>

    @Insert(entity = Cards::class)
    suspend fun insertToCards(card: Card)
}