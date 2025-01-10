package com.example.myapplication.mvvm

import com.example.myapplication.db.CardDao
import com.example.myapplication.models.Card
import com.example.myapplication.models.CardInfo
import com.example.myapplication.utils.RetrofitCard

class CardRepository(private val retrofit: RetrofitCard, private val cardDao: CardDao) {
    suspend fun getCardFromNet(number: Int): retrofit2.Response<CardInfo>? {
        return try {
            retrofit.getCard(number)
        } catch (e: Exception) {
            null
        }
    }

    fun getCardFromDb(number: Int): Card {
        return cardDao.getCard(number)
    }

    fun getAllCardsFromDb(): List<Card> {
        return cardDao.getAllCard()
    }

    suspend fun insertToCards(
        card: Card
    ) {
        cardDao.insertToCards(card)
    }
}