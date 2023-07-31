package com.andreisingeleytsev.myhabbitsapp.ui.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.andreisingeleytsev.myhabbitsapp.ui.screens.main_screen.MainScreen
import com.andreisingeleytsev.myhabbitsapp.ui.theme.MainColor
import com.andreisingeleytsev.myhabbitsapp.ui.utils.Fonts
import com.andreisingeleytsev.sportgameapp.ui.screens.rules_screen.RulesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val firstLaunch = mutableStateOf(sharedPreferences.getBoolean("key", true))
        Log.d("tag", firstLaunch.value.toString())
        setContent {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(MainColor), contentAlignment = Alignment.Center){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (firstLaunch.value) {
                        RulesScreen(state = firstLaunch)
                    }
                        else {

                        MainScreen()
                    }
                }
                else {
                    Text(text = "Sorry, but you are using an old android version",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontFamily = Fonts.customFontFamily,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

