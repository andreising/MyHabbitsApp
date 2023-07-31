package com.andreisingeleytsev.myhabbitsapp.ui.utils

sealed class UIEvent{
    data class Navigate(val route: String): UIEvent()
}
