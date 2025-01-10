package com.example.myapplication.views

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.mvvm.CardViewModel
import com.example.myapplication.R
import com.example.myapplication.utils.State
import com.example.myapplication.models.Card
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CardInfoView(cardInfo: Card?) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.padding(top = 10.dp)) {
            Text(
                text = "Номер карты:",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = cardInfo?.number.toString(),
                maxLines = 1,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black)
            )
        }
        Row(modifier = Modifier.padding(top = 10.dp)) {
            Text(
                text = "Страна:",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = cardInfo?.country ?: "",
                maxLines = 1,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black)
            )
        }
        Row(modifier = Modifier.padding(top = 10.dp)) {
            Text(
                text = "Координаты:",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = cardInfo?.coordinates ?: "",
                maxLines = 1,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black)
            )
        }
        Row(modifier = Modifier.padding(top = 10.dp)) {
            Text(
                text = "Тип карты:",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = cardInfo?.scheme ?: "",
                maxLines = 1,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black)
            )
        }
        Row(modifier = Modifier.padding(top = 10.dp)) {
            Text(
                text = "Банк:",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(end = 10.dp)
            )

            Text(
                text = cardInfo?.bank ?: "",
                maxLines = 1,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black)
            )
        }

        Row(modifier = Modifier.padding(top = 10.dp)) {
            Text(
                text = "URL:",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = cardInfo?.url ?: "",
                maxLines = 1,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black)
            )
        }
        Row(modifier = Modifier.padding(top = 10.dp)) {
            Text(
                text = "Телефон:",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = cardInfo?.phone ?: "",
                maxLines = 1,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black)
            )
        }
        Row(
            modifier = Modifier.padding(
                top = 10.dp,
                bottom = 10.dp
            )
        ) {
            Text(
                text = "Город:",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = cardInfo?.city ?: "",
                maxLines = 1,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black)
            )
        }
    }
}

@Composable
fun CardSearchView(viewModel: CardViewModel) {
    val cardInfo = remember { mutableStateOf<Card?>(null) }
    var cardNumber: Int
    val context = LocalContext.current
    val text = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    val isButtonEnabled = remember { mutableStateOf(false) }
    val isTextFieldEnabled = remember { mutableStateOf(true) }
    val isCardInfoVisible = remember { mutableStateOf(false) }
    val isProgressBarVisible = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    fun validate(text: String) {
        isError.value = !(text.matches(Regex("[0-9]{8}")))
        isButtonEnabled.value = text.matches(Regex("[0-9]{8}"))
    }

    Column {
        Text(text = "Введите первые 8 цифр номера карты", modifier = Modifier.padding(10.dp))
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 20.dp
            )
        ) {
            OutlinedTextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                    validate(text.value)
                },
                shape = RoundedCornerShape(
                    topEnd = 30.dp,
                    bottomEnd = 30.dp,
                    topStart = 30.dp,
                    bottomStart = 30.dp
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = isError.value,
                enabled = isTextFieldEnabled.value,
                supportingText = {
                    if (isError.value) {
                        Text(
                            text = "Номер должен содержать первые 8 цифр",
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                trailingIcon = {
                    if (isError.value)
                        Icon(
                            Icons.Filled.Warning,
                            "error",
                            tint = MaterialTheme.colorScheme.error
                        )
                },
                keyboardActions = KeyboardActions { validate(text.value) }
            )
            Button(
                onClick = {
                    coroutineScope.launch {
                        isButtonEnabled.value = false
                        isTextFieldEnabled.value = false
                        isProgressBarVisible.value = true
                        isCardInfoVisible.value = false
                        cardNumber = text.value.toInt()
                        delay(300)
                        viewModel.getCard(text.value.toInt())
                        viewModel.state.collect { state ->
                            when (state) {
                                State.Success -> {
                                    cardInfo.value = viewModel.cardInfo
                                    isCardInfoVisible.value = true
                                    isButtonEnabled.value = false
                                    isTextFieldEnabled.value = true
                                    isProgressBarVisible.value = false
                                    text.value = ""
                                }

                                is State.Error -> {
                                    text.value = ""
                                    isCardInfoVisible.value = false
                                    isButtonEnabled.value = false
                                    isTextFieldEnabled.value = true
                                    isProgressBarVisible.value = false
                                    Toast.makeText(
                                        context,
                                        state.errorMessage.toString(),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }

                },
                modifier = Modifier.padding(
                    start = 15.dp,
                    top = 15.dp
                ),
                enabled = isButtonEnabled.value
            ) {
                Text(text = "Поиск", fontSize = 14.sp)
            }
        }
        AnimatedVisibility(
            visible = isProgressBarVisible.value,
            modifier = Modifier
                .padding(top = 40.dp)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            CircularProgressIndicator(color = colorResource(id = R.color.purple_40))
        }
        AnimatedVisibility(
            visible = isCardInfoVisible.value,
            modifier = Modifier
                .padding(top = 40.dp, start = 10.dp, end = 10.dp)
        ) {
            CardInfoView(cardInfo = cardInfo.value)
        }
    }
}

@Composable
fun ShowCardItem(cardInfo: Card?) {
    Divider(
        thickness = 3.dp,
        color = colorResource(id = R.color.purple_40),
        modifier = Modifier.fillMaxWidth()
    )
    CardInfoView(cardInfo = cardInfo)
    Divider(
        thickness = 3.dp, color = colorResource(id = R.color.purple_40), modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    )
}

@Composable
fun ShowCardSearchHistory(viewModel: CardViewModel) {
    val cardsList = viewModel.getAllCardsFromDb()
    if (cardsList.isEmpty()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Нет данных", fontSize = 16.sp)
        }
    }
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        items(count = cardsList.size) { index -> ShowCardItem(cardsList[index]) }
    }
}