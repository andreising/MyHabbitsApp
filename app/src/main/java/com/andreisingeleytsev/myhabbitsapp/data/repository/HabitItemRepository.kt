package com.andreisingeleytsev.myhabbitsapp.data.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreisingeleytsev.myhabbitsapp.data.entity.HabitItem
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface HabitItemRepository {
    suspend fun insertItem(item: HabitItem)
    suspend fun deleteItem(item: HabitItem)
    fun getItems(date: LocalDate): Flow<List<HabitItem>>
}