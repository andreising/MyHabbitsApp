package com.andreisingeleytsev.myhabbitsapp.domain.implementation

import android.os.Build
import androidx.annotation.RequiresApi
import com.andreisingeleytsev.myhabbitsapp.data.entity.HabitItem
import com.andreisingeleytsev.myhabbitsapp.domain.provider.CurrentDateProvider
import java.time.LocalDate

class CurrentDateProviderImpl: CurrentDateProvider {
    @RequiresApi(Build.VERSION_CODES.O)
    private var currentDate = LocalDate.now()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getDate(): LocalDate {
        return currentDate
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun insertDate(date: LocalDate) {
        currentDate = date
    }


}