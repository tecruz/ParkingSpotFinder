package com.tecruz.parkingspotfinder.presentation

import com.google.android.gms.maps.model.LatLng
import com.tecruz.parkingspotfinder.domain.model.ParkingSpot

sealed class MapEvent {
    object ToggleFalloutMap : MapEvent()
    data class OnMapLongClick(val latLng: LatLng) : MapEvent()
    data class OnInfoWindowLongClick(val parkingSpot: ParkingSpot) : MapEvent()
}
