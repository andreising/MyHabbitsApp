package com.andreisingeleytsev.myhabbitsapp.ui.screens.famous_people_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andreisingeleytsev.myhabbitsapp.R
import com.andreisingeleytsev.myhabbitsapp.ui.theme.MainColor
import com.andreisingeleytsev.myhabbitsapp.ui.theme.SelectedColor
import com.andreisingeleytsev.myhabbitsapp.ui.utils.Fonts


@Composable
fun FamousPeopleScreen(paddingValues: PaddingValues, viewModel: FamousPeopleScreenViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
            .padding(paddingValues = paddingValues)
    ) {
        Column(Modifier.fillMaxSize()) {
            Text(
                text = "Habits of famous people",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = SelectedColor,
                    fontFamily = Fonts.customFontFamily,
                    fontStyle = FontStyle.Italic
                ),
                textAlign = TextAlign.Center,

            )
            if (viewModel.isMainListOpen.value){
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 28.dp),
                    textAlign = TextAlign.Center,
                    text = "Popular",
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = Fonts.customFontFamily
                    )
                )
                LazyColumn(modifier = Modifier.fillMaxSize()){
                    items(viewModel.list){item ->
                        ListItem(person = item, viewModel.list.indexOf(item))
                    }
                }
            } else {
                PersonInfo(viewModel.person.value)
            }
            
        }
    }
}


@Composable
fun ListItem(
    person: Pair<String, String>,
    index: Int,
    viewModel: FamousPeopleScreenViewModel = hiltViewModel()
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = person.first,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = Fonts.customFontFamily
            )
        )
        IconButton(onClick = {
            viewModel.onEvent(FamousPeopleScreenEvent.OnPeopleChoose(index))
        }) {
            Icon(
                painter = painterResource(id = R.drawable.vector_left),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}


@Composable
fun PersonInfo(
    person: Pair<String, String>,
    viewModel: FamousPeopleScreenViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 5.dp)
                .scrollable(rememberScrollState(), orientation = Orientation.Vertical), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = person.first,
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = Fonts.customFontFamily
                ))
            Text(text = person.second,
                style = TextStyle(
                    fontSize = 22.sp,
                    color = Color.White,
                    fontFamily = Fonts.customFontFamily
                ), modifier = Modifier.padding(top = 5.dp))
        }
        IconButton(onClick = {
            Log.d("tag", "check")
            viewModel.onEvent(FamousPeopleScreenEvent.OnBack)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.vector_right),
                contentDescription = null,
                tint = Color.White
            )
        }
    }

}