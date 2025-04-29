package com.tecruz.parkingspotfinder.presentation

import com.google.maps.android.compose.MapProperties
import com.tecruz.parkingspotfinder.domain.model.ParkingSpot

data class MapState(
    val properties: MapProperties = MapProperties(),
    val isFalloutMap: Boolean = false,
    val parkingSpots: List<ParkingSpot> = emptyList()
)
