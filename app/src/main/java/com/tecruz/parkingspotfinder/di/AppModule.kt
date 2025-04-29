package com.tecruz.parkingspotfinder.di

import android.content.Context
import androidx.room.Room
import com.tecruz.parkingspotfinder.data.ParkingSpotDatabase
import com.tecruz.parkingspotfinder.data.ParkingSpotRepositoryImpl
import com.tecruz.parkingspotfinder.domain.repository.ParkingSpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideParkingSpotDatabase(
        @ApplicationContext context: Context
    ): ParkingSpotDatabase {
        return Room.databaseBuilder(
            context,
            ParkingSpotDatabase::class.java,
            ParkingSpotDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideParkingSpotRepository(db: ParkingSpotDatabase): ParkingSpotRepository {
        return ParkingSpotRepositoryImpl(db.parkingSpotDao)
    }
}