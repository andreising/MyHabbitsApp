package com.andreisingeleytsev.myhabbitsapp.ui.screens.add_habits_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.myhabbitsapp.data.entity.HabitItem
import com.andreisingeleytsev.myhabbitsapp.data.repository.HabitItemRepository
import com.andreisingeleytsev.myhabbitsapp.domain.provider.CurrentDateProvider
import com.andreisingeleytsev.myhabbitsapp.ui.screens.calendar_date_screen.CalendarDateScreenEvent
import com.andreisingeleytsev.myhabbitsapp.ui.utils.Routes
import com.andreisingeleytsev.myhabbitsapp.ui.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddHabitsScreenViewModel @Inject constructor(
    val habitItemRepository: HabitItemRepository,
    val currentDateProvider: CurrentDateProvider
):ViewModel() {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun onEvent(event: AddHabitsScreenEvent){
        when(event){
            is AddHabitsScreenEvent.OnMainButtonPressed->{
                if (name.value.isEmpty()) borderColor.value = Color.Red
                    else{
                        viewModelScope.launch {
                            val date = currentDateProvider.getDate()
                            habitItemRepository.insertItem(HabitItem(null, name.value, date.toString()))
                            sendUIEvent(UIEvent.Navigate(Routes.CALENDAR_DATE_SCREEN))
                        }
                    }
            }
        }
    }

    private fun sendUIEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }


    val name = mutableStateOf("")
    val borderColor = mutableStateOf(Color.White)

}