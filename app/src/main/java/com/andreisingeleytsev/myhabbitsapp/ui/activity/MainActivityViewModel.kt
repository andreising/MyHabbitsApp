package com.andreisingeleytsev.myhabbitsapp.ui.activity

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    var context: Activity? = null

}