package com.andrei_singeleytsev.sportquizapp.presentation.menu

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.andreisingeleytsev.myhabbitsapp.ui.theme.MainColor
import com.andreisingeleytsev.myhabbitsapp.ui.theme.SelectedColor


@Composable
fun BottomNavigationMenu(currentRoute: String?,onNavigate: (String)->Unit) {
    val listItems = listOf(
        BottomNavigationItem.AddHabitItem,
        BottomNavigationItem.CalendarDateItem,
        BottomNavigationItem.FamousPeopleItem
    )
    BottomAppBar(
        containerColor = Color.Transparent
    ) {
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute==item.route,
                onClick = {
                          onNavigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon_id),
                        contentDescription = "bottom_icon"
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SelectedColor,
                    unselectedIconColor = Color.White,
                    indicatorColor = MainColor
                )
            )

        }
    }
}