package com.andreisingeleytsev.myhabbitsapp.di

import android.app.Application
import androidx.room.Room
import com.andreisingeleytsev.myhabbitsapp.data.MainDataBase
import com.andreisingeleytsev.myhabbitsapp.data.repository.HabitItemRepository
import com.andreisingeleytsev.myhabbitsapp.data.repository.implementation.HabitItemRepositoryImpl
import com.andreisingeleytsev.myhabbitsapp.domain.implementation.CurrentDateProviderImpl
import com.andreisingeleytsev.myhabbitsapp.domain.provider.CurrentDateProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule{
    @Provides
    @Singleton
    fun provideMainDatabase(app: Application): MainDataBase {
        return Room.databaseBuilder(
            app,
            MainDataBase::class.java,
            "sport_quiz_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHabitItemRepository(db: MainDataBase): HabitItemRepository {
        return HabitItemRepositoryImpl(db.habitItemDao)
    }
    @Provides
    @Singleton
    fun provideAddCurrentDate(db: MainDataBase): CurrentDateProvider {
        return CurrentDateProviderImpl()
    }
}