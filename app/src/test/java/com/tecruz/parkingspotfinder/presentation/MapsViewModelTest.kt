package com.tecruz.parkingspotfinder.presentation

import com.google.android.gms.maps.model.LatLng
import com.tecruz.parkingspotfinder.domain.model.ParkingSpot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MapsViewModelTest {

    private lateinit var viewModel: MapsViewModel
    private lateinit var repository: FakeParkingSpotRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeParkingSpotRepository()
    }

    @Test
    fun `onEvent ToggleFalloutMap should toggle isFalloutMap`() = runTest(testDispatcher) {
        viewModel = MapsViewModel(repository)
        advanceUntilIdle()

        assertFalse(viewModel.state.isFalloutMap)
        assertNull(viewModel.state.properties.mapStyleOptions)

        viewModel.onEvent(MapEvent.ToggleFalloutMap)

        assertTrue(viewModel.state.isFalloutMap)
    }

    @Test
    fun `onEvent OnMapLongClick should insert parking spot`() = runTest(testDispatcher) {
        viewModel = MapsViewModel(repository)
        advanceUntilIdle()

        val latLng = LatLng(10.0, 20.0)
        viewModel.onEvent(MapEvent.OnMapLongClick(latLng))

        advanceUntilIdle()

        assertEquals(1, repository.spots.size)
        assertEquals(10.0, repository.spots.first().lat, 0.0)
        assertEquals(20.0, repository.spots.first().lng, 0.0)
    }

    @Test
    fun `onEvent OnInfoWindowLongClick should delete parking spot`() = runTest(testDispatcher) {
        val parkingSpot = ParkingSpot(10.0, 20.0, 1)
        repository.setSpots(listOf(parkingSpot))
        viewModel = MapsViewModel(repository)
        advanceUntilIdle()

        assertEquals(1, viewModel.state.parkingSpots.size)

        viewModel.onEvent(MapEvent.OnInfoWindowLongClick(parkingSpot))
        advanceUntilIdle()

        assertTrue(repository.spots.isEmpty())
        assertTrue(viewModel.state.parkingSpots.isEmpty())
    }

    @Test
    fun `getParkingSpots should update state`() = runTest(testDispatcher) {
        val parkingSpots = listOf(ParkingSpot(10.0, 20.0, 1))
        repository.setSpots(parkingSpots)
        viewModel = MapsViewModel(repository)

        advanceUntilIdle()

        assertEquals(parkingSpots, viewModel.state.parkingSpots)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
