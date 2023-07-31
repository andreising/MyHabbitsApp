package com.andreisingeleytsev.myhabbitsapp.ui.screens.calendar_date_screen

import java.time.format.TextStyle.SHORT
import android.os.Build
import android.util.Log
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.myhabbitsapp.R
import com.andreisingeleytsev.myhabbitsapp.data.entity.HabitItem
import com.andreisingeleytsev.myhabbitsapp.ui.screens.add_habits_screen.AddHabitsScreenEvent
import com.andreisingeleytsev.myhabbitsapp.ui.theme.MainColor
import com.andreisingeleytsev.myhabbitsapp.ui.theme.SelectedColor
import com.andreisingeleytsev.myhabbitsapp.ui.utils.Fonts
import com.andreisingeleytsev.myhabbitsapp.ui.utils.UIEvent
import io.github.boguszpawlowski.composecalendar.Calendar
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.SelectableWeekCalendar
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.day.NonSelectableDayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDateScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    viewModel: CalenderDateScreenViewModel = hiltViewModel()
) {
    var isCalendarOpen by remember {
        viewModel.isCalendarOpen
    }
    var currentDate by remember {
        viewModel.currentDate
    }
    val list = viewModel.habitsList.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{
            when(it) {
                is UIEvent.Navigate -> {
                    navHostController.navigate(it.route)
                }
                else -> {}
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
            .padding(paddingValues = paddingValues)
    ) {
        Column(Modifier.fillMaxSize()) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = {
                    viewModel.onEvent(CalendarDateScreenEvent.OnCalendarButtonPressed)
                }) {
                    Icon(painter = painterResource(id = R.drawable.calendar), contentDescription = null, tint = Color.White)
                }
            }
            if (!isCalendarOpen){
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        viewModel.onEvent(
                            CalendarDateScreenEvent.OnDayChanged(
                                currentDate.minusDays(
                                    1
                                )
                            )
                        )

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.vector_right),
                            contentDescription = null
                        )
                    }
                    Text(
                        text = currentDate.toString(),
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = SelectedColor,
                            fontFamily = Fonts.customFontFamily
                        ),
                        modifier = Modifier.padding(start = 20.dp, end = 10.dp)
                    )
                    IconButton(onClick = {
                        viewModel.onEvent(
                            CalendarDateScreenEvent.OnDayChanged(
                                currentDate.plusDays(
                                    1
                                )
                            )
                        )
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.vector_left),
                            contentDescription = null
                        )
                    }
                }
                if (list.value.isEmpty()){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Button(onClick = {
                            viewModel.onEvent(CalendarDateScreenEvent.OnAddButtonPressed)
                        }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red, contentColor = Color.White
                        )) {

                            Text(text = "Add habits",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontFamily = Fonts.customFontFamily
                                ),
                                modifier = Modifier.padding(10.dp))
                        }
                    }

                } else{
                    LazyColumn(Modifier.fillMaxSize()){
                        items(list.value){
                            com.andreisingeleytsev.myhabbitsapp.ui.screens.calendar_date_screen.HabitItem(
                                habitItem = it
                            )
                        }
                    }
                }
            }
            else {

                val calendarState = rememberSelectableCalendarState()

                Column(
                    Modifier.verticalScroll(rememberScrollState())
                ) {
                    SelectableCalendar(calendarState = calendarState, today = currentDate,
                    dayContent = { DayContent(dayState = it as NonSelectableDayState)},
                        daysOfWeekHeader = { WeekHeader(daysOfWeek = it) },
                        monthHeader = { MonthHeader(monthState = it) },
                        )


                }

            }


        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DayContent(dayState: NonSelectableDayState,
                       viewModel: CalenderDateScreenViewModel = hiltViewModel()) {
    val backgroundColor = when(dayState.date){
        LocalDate.now()->Color.Red
        viewModel.currentDate.value -> Color.Green

        else -> {
            Color.Transparent
        }
    }

    Box(modifier = Modifier
        .wrapContentSize()
        .background(backgroundColor)
        .border(0.25.dp, SelectedColor)){
        Text(
            text = dayState.date.dayOfMonth.toString(),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = Fonts.customFontFamily
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, end = 4.dp)
                .clickable {
                    viewModel.onEvent(CalendarDateScreenEvent.OnCalendarDayPressed(dayState.date))
                },
            textAlign = TextAlign.Center,

            )
    }

}

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun WeekHeader(daysOfWeek: List<DayOfWeek>) {
        Row(modifier = Modifier
            .background(SelectedColor)
            .graphicsLayer {
                clip = true //make sure to set clip to true
                shape = RoundedCornerShape(20.dp)
            }) {
            daysOfWeek.forEach { dayOfWeek ->
                Text(
                    textAlign = TextAlign.Center,
                    text = dayOfWeek.getDisplayName(SHORT, Locale.ROOT),
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = Fonts.customFontFamily
                    )
                )
            }
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MonthHeader(monthState: MonthState) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        IconButton(onClick = {
            monthState.currentMonth = monthState.currentMonth.minusMonths(1)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.vector_right),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(35.dp))
        Text(monthState.currentMonth.month.name, style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = Fonts.customFontFamily
        ))
        Spacer(modifier = Modifier.width(35.dp))
        IconButton(onClick = {
            monthState.currentMonth = monthState.currentMonth.plusMonths(1)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.vector_left),
                contentDescription = null
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitItem(habitItem: HabitItem, viewModel: CalenderDateScreenViewModel = hiltViewModel()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(MainColor)
            ,
        shape = RoundedCornerShape(37.dp),
        border = BorderStroke(1.dp, Color.White)
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .background(MainColor),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(modifier = Modifier.padding(start = 15.dp),
                text = habitItem.name, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = Fonts.customFontFamily
            ),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    viewModel.onEvent(CalendarDateScreenEvent.DeleteItem(habitItem))
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.Red)
                }
                Checkbox(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(end = 5.dp),
                    checked = habitItem.isDone, onCheckedChange = {
                        viewModel.onEvent(CalendarDateScreenEvent.OnHabitChecked(habitItem.copy(isDone = it)))
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MainColor,
                        uncheckedColor = Color.Red,
                        checkmarkColor = Color.Red
                    )
                )
            }

        }

    }
}

