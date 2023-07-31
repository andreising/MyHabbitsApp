package com.andreisingeleytsev.myhabbitsapp.ui.screens.famous_people_screen

sealed class FamousPeopleScreenEvent{
    data class OnPeopleChoose(val index: Int): FamousPeopleScreenEvent()
    object OnBack: FamousPeopleScreenEvent()
}
