package com.andreisingeleytsev.myhabbitsapp.ui.screens.calendar_date_screen

import com.andreisingeleytsev.myhabbitsapp.data.entity.HabitItem
import java.time.LocalDate

sealed class CalendarDateScreenEvent{
    object OnCalendarButtonPressed: CalendarDateScreenEvent()
    object OnAddButtonPressed: CalendarDateScreenEvent()
    data class OnCalendarDayPressed(val date: LocalDate): CalendarDateScreenEvent()
    data class OnDayChanged(val date: LocalDate): CalendarDateScreenEvent()
    data class OnHabitChecked(val habitItem: HabitItem): CalendarDateScreenEvent()
    data class DeleteItem(val habitItem: HabitItem): CalendarDateScreenEvent()
}
