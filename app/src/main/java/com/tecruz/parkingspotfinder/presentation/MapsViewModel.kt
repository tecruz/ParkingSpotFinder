package com.tecruz.parkingspotfinder.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.MapStyleOptions
import com.tecruz.parkingspotfinder.domain.model.ParkingSpot
import com.tecruz.parkingspotfinder.domain.repository.ParkingSpotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val parkingSpotRepository: ParkingSpotRepository
) : ViewModel() {

    var state by mutableStateOf(MapState())

    init {
        viewModelScope.launch {
            parkingSpotRepository.getParkingSpots().collectLatest { parkingSpots ->
                state = state.copy(
                    parkingSpots = parkingSpots
                )
            }
        }
    }

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.ToggleFalloutMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if (state.isFalloutMap) {
                            null
                        } else MapStyleOptions(MapStyle.json)
                    ),
                    isFalloutMap = !state.isFalloutMap
                )
            }

            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch {
                    parkingSpotRepository.deleteParkingSpot(
                        event.parkingSpot
                    )
                }
            }

            is MapEvent.OnMapLongClick -> {
                viewModelScope.launch {
                    parkingSpotRepository.insertParkingSpot(
                        ParkingSpot(
                            lat = event.latLng.latitude,
                            lng = event.latLng.longitude,
                        )
                    )
                }
            }
        }
    }
}