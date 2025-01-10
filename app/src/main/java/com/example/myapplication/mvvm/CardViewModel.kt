package com.example.myapplication.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.Card
import com.example.myapplication.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CardViewModel(private val repository: CardRepository) : ViewModel() {
    val state = MutableStateFlow<State>(State.Success)
    var cardInfo: Card? = null

    fun getCard(number: Int) {
        var isReady = false
        viewModelScope.launch(Dispatchers.IO) {
            val requestFromDb = getCardFromDb(number)
            if (requestFromDb == null) {
                getCardFromNet(number)
            } else {
                cardInfo = requestFromDb
            }
            isReady = true
        }
        while (!isReady) {
            viewModelScope.launch(Dispatchers.IO) {
                delay(300)
            }
        }
    }

    private fun getCardFromDb(number: Int): Card? {
        var cardFromDb: Card? = null
        var isReady = false
        viewModelScope.launch(Dispatchers.IO) {
            val card = repository.getCardFromDb(number)
            if (card != null) {
                cardFromDb = card
               }
            isReady = true
        }
        while (!isReady) {
            viewModelScope.launch(Dispatchers.IO) {
                delay(300)
            }
        }
        return cardFromDb
    }

    private fun getCardFromNet(number: Int) {
        var isReady = false
        viewModelScope.launch(Dispatchers.IO) {
            val requestFromNet = repository.getCardFromNet(number)
            if (requestFromNet != null) {
                if (requestFromNet.isSuccessful) {
                    val result = requestFromNet.body()
                    var coordinates = ""
                    if (result?.country?.name != null)
                        coordinates = "${result.country.latitude}, ${result.country.longitude}"
                    cardInfo = Card(
                        number,
                        result?.country?.name ?: "",
                        coordinates,
                        result?.scheme ?: "",
                        result?.bank?.name ?: "",
                        result?.bank?.url ?: "",
                        result?.bank?.phone ?: "",
                        result?.bank?.city ?: ""
                    )
                    repository.insertToCards(cardInfo!!)
                } else if (requestFromNet.code() == 429) {
                    state.value = State.Error("Превышено количество запросов")
                } else {
                    state.value = State.Error("Произошла ошибка, запрос не выполнен.")
                }
            } else {
                state.value = State.Error("Проверьте подключение к Интернет")
            }
            isReady = true
        }
        while (!isReady) {
            viewModelScope.launch(Dispatchers.IO) {
                delay(300)
            }
        }
    }

    fun getAllCardsFromDb(): List<Card> {
        var isReady = false
        var cardsList = listOf<Card>()
        viewModelScope.launch(Dispatchers.IO) {
            cardsList = repository.getAllCardsFromDb()
            isReady = true
        }
        while (!isReady) {
            viewModelScope.launch(Dispatchers.IO) {
                delay(300)
            }
        }
        return cardsList
    }
}