package com.andreisingeleytsev.myhabbitsapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_item")
data class HabitItem(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val date: String,
    val isDone: Boolean = false
)


