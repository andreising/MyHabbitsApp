package com.andrei_singeleytsev.sportquizapp.presentation.menu

import com.andreisingeleytsev.myhabbitsapp.R
import com.andreisingeleytsev.myhabbitsapp.ui.utils.Routes

sealed class BottomNavigationItem( val icon_id: Int, val route: String) {
    object AddHabitItem: BottomNavigationItem( R.drawable.add_habits, Routes.ADD_HABITS_SCREEN)
    object CalendarDateItem: BottomNavigationItem( R.drawable.calendar_date, Routes.CALENDAR_DATE_SCREEN)
    object FamousPeopleItem: BottomNavigationItem ( R.drawable.famous_people, Routes.FAMOUS_PEOPLE_SCREEN)
}
