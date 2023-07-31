package com.andreisingeleytsev.myhabbitsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreisingeleytsev.myhabbitsapp.data.dao.HabitItemDao
import com.andreisingeleytsev.myhabbitsapp.data.entity.HabitItem

@Database(
    entities = [HabitItem::class],
    version = 1
)
abstract class MainDataBase: RoomDatabase() {
    abstract val habitItemDao: HabitItemDao
}