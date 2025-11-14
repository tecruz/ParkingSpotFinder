package com.tecruz.parkingspotfinder.di

import android.content.Context
import androidx.room.Room
import com.tecruz.parkingspotfinder.data.ParkingSpotDatabase
import com.tecruz.parkingspotfinder.data.ParkingSpotRepositoryImpl
import com.tecruz.parkingspotfinder.domain.repository.ParkingSpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {

    @Singleton
    @Provides
    fun provideParkingSpotDatabase(
        @ApplicationContext context: Context
    ): ParkingSpotDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            ParkingSpotDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideParkingSpotRepository(db: ParkingSpotDatabase): ParkingSpotRepository {
        return ParkingSpotRepositoryImpl(db.parkingSpotDao)
    }
}
