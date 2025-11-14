package com.tecruz.parkingspotfinder.data

import com.tecruz.parkingspotfinder.domain.model.ParkingSpot
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ParkingSpotRepositoryImplTest {

    private lateinit var repository: ParkingSpotRepositoryImpl
    private val dao: ParkingSpotDao = mockk()

    @Before
    fun setup() {
        repository = ParkingSpotRepositoryImpl(dao)
    }

    @Test
    fun `getParkingSpots returns parking spots`() = runBlocking {
        val parkingSpotEntities = listOf(
            ParkingSpotEntity(10.0, 20.0, 1),
            ParkingSpotEntity(30.0, 40.0, 2)
        )
        val expectedParkingSpots = parkingSpotEntities.map { it.toParkingSpot() }

        every { dao.getAllParkingSpots() } returns flowOf(parkingSpotEntities)

        val result = repository.getParkingSpots().first()

        assertEquals(expectedParkingSpots, result)
    }

    @Test
    fun `insertParkingSpot calls dao`() = runBlocking {
        val parkingSpot = ParkingSpot(10.0, 20.0, 1)
        val parkingSpotEntity = parkingSpot.toParkingSpotEntity()

        coEvery { dao.insertParkingSpot(parkingSpotEntity) } returns Unit

        repository.insertParkingSpot(parkingSpot)

        coVerify { dao.insertParkingSpot(parkingSpotEntity) }
    }

    @Test
    fun `deleteParkingSpot calls dao`() = runBlocking {
        val parkingSpot = ParkingSpot(10.0, 20.0, 1)
        val parkingSpotEntity = parkingSpot.toParkingSpotEntity()

        coEvery { dao.deleteParkingSpot(parkingSpotEntity) } returns Unit

        repository.deleteParkingSpot(parkingSpot)

        coVerify { dao.deleteParkingSpot(parkingSpotEntity) }
    }
}
