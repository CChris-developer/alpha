package com.example.myapplication.utils

import com.example.myapplication.models.BottomNavItem

object Consts {
    const val URL = "https://lookup.binlist.net/"
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Поиск",
            route = "search"
        ),
        BottomNavItem(
            label = "История",
            route = "history"
        )
    )
}