package com.andreisingeleytsev.myhabbitsapp.ui.screens.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andrei_singeleytsev.sportquizapp.presentation.menu.BottomNavigationMenu
import com.andrei_singeleytsev.sportquizapp.presentation.navigation.MainNavigationGraph
import com.andreisingeleytsev.myhabbitsapp.ui.theme.MainColor
import com.andreisingeleytsev.myhabbitsapp.ui.utils.UIEvent

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UIEvent.Navigate -> {
                    navController.navigate(uiEvent.route)
                }
                else ->{}
            }
        }
    }
    BoxWithConstraints(modifier = Modifier.fillMaxSize().background(MainColor)){
        val width = maxWidth
        val height = maxHeight
        val padding = 0.142*width
        Scaffold(modifier = Modifier.background(MainColor).padding(padding), bottomBar = {
            BottomNavigationMenu(currentRoute){route->
                viewModel.onEvent(MainScreenEvent.Navigate(route))
            }
        }) {
            MainNavigationGraph(navController, it)
        }
    }

}