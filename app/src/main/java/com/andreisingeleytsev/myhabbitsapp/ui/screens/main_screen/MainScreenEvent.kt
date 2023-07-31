package com.andreisingeleytsev.myhabbitsapp.ui.screens.main_screen

sealed class MainScreenEvent(){
    data class Navigate(val route: String): MainScreenEvent()
}
