package com.tecruz.parkingspotfinder.data

import com.tecruz.parkingspotfinder.domain.model.ParkingSpot
import com.tecruz.parkingspotfinder.domain.repository.ParkingSpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ParkingSpotRepositoryImpl(
    private val parkingSpotDao: ParkingSpotDao
) : ParkingSpotRepository {
    override suspend fun insertParkingSpot(parkingSpot: ParkingSpot) {
        parkingSpotDao.insertParkingSpot(parkingSpot.toParkingSpotEntity())
    }

    override suspend fun deleteParkingSpot(parkingSpot: ParkingSpot) {
        parkingSpotDao.deleteParkingSpot(parkingSpot.toParkingSpotEntity())
    }

    override fun getParkingSpots(): Flow<List<ParkingSpot>> {
        return parkingSpotDao.getAllParkingSpots().map { parkingSpotEntities ->
            parkingSpotEntities.map { it.toParkingSpot() }
        }
    }
}