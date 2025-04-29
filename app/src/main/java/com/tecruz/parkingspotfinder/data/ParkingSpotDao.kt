package com.tecruz.parkingspotfinder.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingSpotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParkingSpot(parkingSpot: ParkingSpotEntity)

    @Delete
    suspend fun deleteParkingSpot(parkingSpot: ParkingSpotEntity)

    @Query("SELECT * FROM parkingspotentity")
    fun getAllParkingSpots(): Flow<List<ParkingSpotEntity>>
}