package com.andreisingeleytsev.myhabbitsapp.ui.screens.calendar_date_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.myhabbitsapp.data.repository.HabitItemRepository
import com.andreisingeleytsev.myhabbitsapp.domain.provider.CurrentDateProvider
import com.andreisingeleytsev.myhabbitsapp.ui.utils.Routes
import com.andreisingeleytsev.myhabbitsapp.ui.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalenderDateScreenViewModel @Inject constructor(
    private val habitItemRepository: HabitItemRepository,
    private val currentDateProvider: CurrentDateProvider
): ViewModel() {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: CalendarDateScreenEvent){
        when(event){
            is CalendarDateScreenEvent.OnCalendarDayPressed -> {
                currentDateProvider.insertDate(event.date)
                currentDate.value = event.date
                updateList(event.date)
                isCalendarOpen.value = false
            }
            is CalendarDateScreenEvent.OnCalendarButtonPressed -> {
                isCalendarOpen.value = !isCalendarOpen.value
            }
            is CalendarDateScreenEvent.OnDayChanged -> {
                currentDateProvider.insertDate(event.date)
                currentDate.value = event.date
                updateList(event.date)
            }
            is CalendarDateScreenEvent.OnHabitChecked -> {
                viewModelScope.launch {
                    habitItemRepository.insertItem(event.habitItem)
                }
            }
            is CalendarDateScreenEvent.OnAddButtonPressed -> {
                sendUIEvent(UIEvent.Navigate(Routes.ADD_HABITS_SCREEN))
            }
            is CalendarDateScreenEvent.DeleteItem -> {
                viewModelScope.launch {
                    habitItemRepository.deleteItem(event.habitItem)
                }
            }
        }
    }

    private fun sendUIEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    val isCalendarOpen = mutableStateOf(false)
    @RequiresApi(Build.VERSION_CODES.O)
    val currentDate = mutableStateOf(currentDateProvider.getDate())
    @RequiresApi(Build.VERSION_CODES.O)
    var habitsList = habitItemRepository.getItems(currentDate.value)
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateList(date: LocalDate){
        habitsList = habitItemRepository.getItems(date)
    }
}