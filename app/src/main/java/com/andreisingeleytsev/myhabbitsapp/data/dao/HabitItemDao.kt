package com.andreisingeleytsev.myhabbitsapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreisingeleytsev.myhabbitsapp.data.entity.HabitItem
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: HabitItem)
    @Delete
    suspend fun deleteItem(item: HabitItem)
    @Query("SELECT * FROM habit_item WHERE date IS :date")
    fun getItems(date: String): Flow<List<HabitItem>>
}

