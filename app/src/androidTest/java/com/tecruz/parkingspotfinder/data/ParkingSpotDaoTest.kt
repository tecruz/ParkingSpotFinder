package com.tecruz.parkingspotfinder.data

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@SmallTest
class ParkingSpotDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: ParkingSpotDatabase
    private lateinit var dao: ParkingSpotDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.parkingSpotDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertParkingSpot_shouldSaveParkingSpot() = runTest {
        val parkingSpot = ParkingSpotEntity(10.0, 20.0, 1)
        dao.insertParkingSpot(parkingSpot)

        dao.getAllParkingSpots().test {
            val list = awaitItem()
            assertTrue(list.contains(parkingSpot))
            cancel()
        }
    }

    @Test
    fun deleteParkingSpot_shouldDeleteParkingSpot() = runTest {
        val parkingSpot = ParkingSpotEntity(10.0, 20.0, 1)
        dao.insertParkingSpot(parkingSpot)
        dao.deleteParkingSpot(parkingSpot)

        dao.getAllParkingSpots().test {
            val list = awaitItem()
            assertFalse(list.contains(parkingSpot))
            cancel()
        }
    }
}
