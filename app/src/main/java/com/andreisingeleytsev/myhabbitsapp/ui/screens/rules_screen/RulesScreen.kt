package com.andreisingeleytsev.sportgameapp.ui.screens.rules_screen

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andreisingeleytsev.myhabbitsapp.R
import com.andreisingeleytsev.myhabbitsapp.ui.theme.MainColor
import com.andreisingeleytsev.myhabbitsapp.ui.utils.Fonts
import com.andreisingeleytsev.myhabbitsapp.ui.utils.UIEvent


@Composable
fun RulesScreen(state: MutableState<Boolean>, viewModel: RulesScreenViewModel = hiltViewModel()){
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UIEvent.Navigate -> {
                    val sharedPreferences =
                         context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
                    sharedPreferences?.edit()?.putBoolean("key", false)?.apply()
                    state.value = false
                }

            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MainColor), contentAlignment = Alignment.Center){

        Column(Modifier.fillMaxWidth().padding(10.dp, top = 100.dp)) {
            Image(painter = painterResource(id = R.drawable.example), contentDescription = null,
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp))
            Text(text = "Add habits for every day to make lifestyle is healthy!",
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = Fonts.customFontFamily,
                    textAlign = TextAlign.Center
                ))
            Button(onClick = {
                viewModel.onEvent(RulesScreenEvent.OnNextButtonPressed)
            }, modifier = Modifier.fillMaxWidth().padding(top = 20.dp), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red, contentColor = Color.White
            )) {
                Text(text = "Understand!")
            }
        }


    }


}