package com.andreisingeleytsev.myhabbitsapp.ui.screens.famous_people_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.myhabbitsapp.ui.screens.calendar_date_screen.CalendarDateScreenEvent
import com.andreisingeleytsev.myhabbitsapp.ui.utils.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class FamousPeopleScreenViewModel: ViewModel() {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun onEvent(event: FamousPeopleScreenEvent){
        when(event){
            is FamousPeopleScreenEvent.OnPeopleChoose -> {
                person.value = list[event.index]
                isMainListOpen.value = false
            }
            is FamousPeopleScreenEvent.OnBack -> {
                isMainListOpen.value = true
            }
        }
    }

    private fun sendUIEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
    val list = LIST_OF_FAMOUS_PEOPLE
    val isMainListOpen = mutableStateOf(true)
    val person = mutableStateOf(Pair("",""))
}

val LIST_OF_FAMOUS_PEOPLE = listOf(
    Pair(
        "Lionel Messi", "Regular training sessions and continuous skill development.\n" +
                "Healthy eating with a focus on proteins, vegetables, and fruits.\n" +
                "Maintaining good sleep hygiene and rest.\n" +
                "Teamwork skills and building strong relationships with teammates.\n" +
                "Self-motivation and striving to achieve new goals."
    ),
    Pair(
        "Serena Williams", "Regular physical workouts and maintaining physical fitness.\n" +
                "Balanced nutrition, including natural and nutritious foods.\n" +
                "Practice of positive thinking and focus on goal achievement.\n" +
                "Deep analysis and self-evaluation of the game for continuous improvement.\n" +
                "Maintaining motivation in challenging situations and despite setbacks."
    ),
    Pair(
        "Usain Bolt", "Regular training for speed and endurance development.\n" +
                "Balanced nutrition with a focus on carbohydrates and healthy fats.\n" +
                "Maintaining a positive attitude and confidence in one's athletic abilities.\n" +
                "Strict adherence to training and rest routines.\n" +
                "Teamwork skills and maintaining sports discipline."
    ),
    Pair(
        "Michael Phelps", "Regular swimming training and maintaining physical endurance.\n" +
                "Healthy eating with a focus on proteins and nutritious foods.\n" +
                "Continuous work on swimming technique and improving performance.\n" +
                "Mental preparation and practice of visualization of success.\n" +
                "Maintaining a high level of motivation and striving to set new records."
    ),
    Pair(
        "Simone Biles", "Intensive training and development of flexibility and strength.\n" +
                "Healthy eating, providing necessary nutrients.\n" +
                "Balancing training and rest to prevent overtraining.\n" +
                "Confidence in one's abilities and constant pursuit of perfection.\n" +
                "Support and interaction with coaches and the team."
    ),
    Pair(
        "Warren Buffett", "Moderate food consumption and portion control.\n" +
                "Daily physical activity, such as walks or yoga.\n" +
                "Preference for natural and fresh food products.\n" +
                "Limitation of alcohol consumption.\n" +
                "Sufficient sleep and regular daily routine."
    ),
    Pair(
        "Mark Zuckerberg",
        "Consumption of nutrient-rich foods, including fruits, vegetables, and nuts.\n" +
                "Regular physical activity, including sports or fitness exercises.\n" +
                "Limitation of sugar and processed food intake.\n" +
                "Practice of meditation or relaxation for stress relief and mental well-being.\n" +
                "Regular medical check-ups and monitoring of health indicators."
    ),
    Pair(
        "Oprah Winfrey", "Plant-based eating, including vegetables, fruits, and grains.\n" +
                "Adequate water consumption for hydration.\n" +
                "Moderate physical activity, such as daily walks or yoga.\n" +
                "Maintaining a proper sleep and rest routine.\n" +
                "Practice of positive thinking and gratitude."
    ),
    Pair(
        "Elon Musk", "Regular workouts and physical exercises to maintain physical fitness.\n" +
                "Healthy eating with a focus on high-quality proteins, vegetables, and beneficial fats.\n" +
                "Limitation of caffeine and other stimulants.\n" +
                "Practice of stress management strategies, such as meditation or deep breathing.\n" +
                "Establishing a regular sleep schedule and adhering to it."
    ),
    Pair(
        "Michelle Obama",
        "Regular physical workouts, including aerobic exercises and strength training.\n" +
                "Balanced diet with an emphasis on vegetables, fruits, and lean proteins.\n" +
                "Sufficient water intake for hydration.\n" +
                "Avoidance of processed foods and fast food.\n" +
                "Maintenance of mental well-being through meditation practices or connecting with loved ones."
    ),
    Pair(
        "Kylie Jenner", "Regular exercise and workouts to maintain physical fitness.\n" +
                "Consistent skincare routine and use of high-quality cosmetics.\n" +
                "Pursuit of self-improvement and exploration of new areas.\n" +
                "Active engagement on social media and ability to interact with fans.\n" +
                "Encouragement of self-expression and creativity."
    ),
    Pair(
        "Dwayne Johnson",
        "Regular physical training and maintaining a healthy physique.\n" +
                "Balanced diet and consumption of high-quality foods.\n" +
                "Continuous development and improvement of skills.\n" +
                "Positive mindset and motivation for oneself and others.\n" +
                "Effective time management and future planning."
    ),
    Pair(
        "Cristiano Ronaldo",
        "Regular training and practice sessions to maintain physical fitness and excel in football.\n" +
                "Strict discipline in diet and adherence to a healthy lifestyle.\n" +
                "Constant drive for self-improvement and reaching new heights.\n" +
                "Teamwork skills and ability to motivate fans.\n" +
                "Positive attitude and attention to detail in all aspects of life."
    ),
    Pair(
        "Ariana Grande", "Regular practice of singing and developing musical abilities.\n" +
                "Embracing a healthy lifestyle, including proper nutrition and physical activity.\n" +
                "Focus on mental well-being and self-care practices such as meditation or yoga.\n" +
                "Interest and engagement in social issues and charitable activities.\n" +
                "Maintaining close relationships with family and friends."
    ),
    Pair(
        "Kim Kardashian",
        "Mindfulness towards personal style and appearance, including skincare and following fashion trends.\n" +
                "Continuous development of business projects and striving for financial independence.\n" +
                "Social media activity and support for her fan community.\n" +
                "Care for family and close relationships.\n" +
                "Drawing attention to social issues and working towards their resolution."
    )
)