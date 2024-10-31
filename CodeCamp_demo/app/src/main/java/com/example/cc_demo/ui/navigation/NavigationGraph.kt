package com.example.cc_demo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cc_demo.ui.views.MainView
import com.example.cc_demo.ui.views.SettingsView


@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = "MainView",
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable("MainView") {
            MainView()
        }
        composable("SettingsView") {
            SettingsView()
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController
){
    BottomAppBar (
        containerColor = Color.Gray,
        contentColor = Color.White
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(selected = currentRoute == "MainView", onClick = {
            navController.navigate("MainView") {
                navController.graph.startDestinationRoute?.let { screenRoute ->
                    popUpTo(screenRoute) {
                        saveState = false
                        inclusive = false
                    }
                }
                launchSingleTop = true
                restoreState = false }
        }, icon = { Icon(Icons.Default.Home, contentDescription = "Home")})

        NavigationBarItem(selected = currentRoute == "SettingsView", onClick = {
            navController.navigate("SettingsView") {
                navController.graph.startDestinationRoute?.let { screenRoute ->
                    popUpTo(screenRoute) {
                        saveState = false
                        inclusive = false
                    }
                }
                launchSingleTop = true
                restoreState = false }
        }, icon = { Icon(Icons.Default.Menu, contentDescription = "Settings")})
    }
}