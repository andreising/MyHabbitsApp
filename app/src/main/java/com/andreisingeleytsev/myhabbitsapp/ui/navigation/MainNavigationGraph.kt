package com.andrei_singeleytsev.sportquizapp.presentation.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andreisingeleytsev.myhabbitsapp.ui.screens.add_habits_screen.AddHabitsScreen
import com.andreisingeleytsev.myhabbitsapp.ui.screens.calendar_date_screen.CalendarDateScreen
import com.andreisingeleytsev.myhabbitsapp.ui.screens.famous_people_screen.FamousPeopleScreen
import com.andreisingeleytsev.myhabbitsapp.ui.utils.Routes


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigationGraph(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(navController = navController, startDestination = Routes.ADD_HABITS_SCREEN) {

        composable(Routes.ADD_HABITS_SCREEN){
            EnterAnimation {
                AddHabitsScreen(paddingValues, navController)
            }
        }
        composable(Routes.CALENDAR_DATE_SCREEN){
            EnterAnimation {
                CalendarDateScreen(paddingValues, navController)
            }

        }
        composable(Routes.FAMOUS_PEOPLE_SCREEN){
            EnterAnimation {
                FamousPeopleScreen(paddingValues)
            }

        }

        }
    }
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = content,
        initiallyVisible = false
    )
}