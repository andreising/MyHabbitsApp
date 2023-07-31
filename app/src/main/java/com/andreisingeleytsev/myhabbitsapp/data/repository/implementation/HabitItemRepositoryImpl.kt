package com.andreisingeleytsev.myhabbitsapp.data.repository.implementation

import com.andreisingeleytsev.myhabbitsapp.data.dao.HabitItemDao
import com.andreisingeleytsev.myhabbitsapp.data.entity.HabitItem
import com.andreisingeleytsev.myhabbitsapp.data.repository.HabitItemRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class HabitItemRepositoryImpl(
    private val dao:HabitItemDao
): HabitItemRepository {
    override suspend fun insertItem(item: HabitItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: HabitItem) {
        dao.deleteItem(item)
    }

    override fun getItems(date: LocalDate): Flow<List<HabitItem>> {
        return dao.getItems(date.toString())
    }
}