package com.pucetec.exam2.services

import com.pucetec.exam2.models.Parking
import com.pucetec.exam2.repositories.ParkingRepository
import org.springframework.stereotype.Service

@Service
class ParkingService(
    private val parkingRepository: ParkingRepository
) {

    fun getAllParkings(): List<Parking> {
        return parkingRepository.findAll()
    }

    fun getParkingByLevel(level: Int): Parking? {
        return parkingRepository.findAll().firstOrNull { it.level == level }
    }
}
