package com.tecruz.parkingspotfinder.presentation

import com.tecruz.parkingspotfinder.domain.model.ParkingSpot
import com.tecruz.parkingspotfinder.domain.repository.ParkingSpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeParkingSpotRepository : ParkingSpotRepository {

    private val spotsFlow = MutableStateFlow<List<ParkingSpot>>(emptyList())
    private var nextId = 1

    val spots: List<ParkingSpot> get() = spotsFlow.value

    fun setSpots(spots: List<ParkingSpot>) {
        spotsFlow.value = spots
        val maxId = spots.mapNotNull { it.id }.maxOrNull() ?: 0
        nextId = maxId + 1
    }

    override suspend fun insertParkingSpot(parkingSpot: ParkingSpot) {
        spotsFlow.update { currentSpots ->
            currentSpots + parkingSpot.copy(id = nextId++)
        }
    }

    override suspend fun deleteParkingSpot(parkingSpot: ParkingSpot) {
        spotsFlow.update { currentSpots ->
            currentSpots.filterNot { it.id == parkingSpot.id }
        }
    }

    override fun getParkingSpots(): Flow<List<ParkingSpot>> {
        return spotsFlow
    }
}
