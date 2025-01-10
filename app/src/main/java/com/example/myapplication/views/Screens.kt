package com.example.myapplication.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.mvvm.CardViewModel

@Composable
fun SearchScreen(viewModel: CardViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CardSearchView(viewModel = viewModel)
    }
}

@Composable
fun HistoryScreen(viewModel: CardViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ShowCardSearchHistory(viewModel = viewModel)
    }
}