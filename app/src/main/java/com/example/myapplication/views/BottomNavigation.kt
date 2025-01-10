package com.example.myapplication.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.mvvm.CardViewModel
import com.example.myapplication.utils.Consts
import com.example.myapplication.R

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues, viewModel: CardViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "search",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("search") {
                SearchScreen(viewModel)
            }
            composable("history") {
                HistoryScreen(viewModel)
            }
        })
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.purple_40)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Consts.BottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route)
                },
                icon = {},
                label = {
                    Text(text = navItem.label, color = colorResource(id = R.color.white))
                }
            )
        }
    }
}

@Composable
fun ShowBottomNavigation(viewModel: CardViewModel) {
    val navController = rememberNavController()
    Surface(color = Color.White) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }, content = { padding ->
                NavHostContainer(
                    navController = navController,
                    padding = padding,
                    viewModel = viewModel
                )
            }
        )
    }
}