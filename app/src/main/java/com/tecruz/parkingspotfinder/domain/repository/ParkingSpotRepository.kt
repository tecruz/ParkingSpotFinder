package com.tecruz.parkingspotfinder.domain.repository

import com.tecruz.parkingspotfinder.domain.model.ParkingSpot
import kotlinx.coroutines.flow.Flow

interface ParkingSpotRepository {
    suspend fun insertParkingSpot(parkingSpot: ParkingSpot)

    suspend fun deleteParkingSpot(parkingSpot: ParkingSpot)

    fun getParkingSpots(): Flow<List<ParkingSpot>>
}