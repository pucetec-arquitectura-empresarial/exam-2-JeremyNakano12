package com.pucetec.exam2.services

import com.pucetec.exam2.models.Ticket
import com.pucetec.exam2.models.Parking
import com.pucetec.exam2.repositories.TicketRepository
import com.pucetec.exam2.repositories.ParkingRepository
import com.pucetec.exam2.exceptions.ParkingFullException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TicketService(
    private val ticketRepository: TicketRepository,
    private val parkingRepository: ParkingRepository
) {

    fun addTicket(): Ticket {
        val availableParking = parkingRepository.findAll().firstOrNull { it.hasAvailableSpots() }

        if (availableParking == null) {
            throw ParkingFullException("No available parking spaces.")
        }

        val ticket = Ticket(
            assignedFloor = availableParking,
            assignedParking = availableParking.occupied_spots + 1,
            entryTime = LocalDateTime.now()
        )

        ticketRepository.save(ticket)

        availableParking.addTicket(ticket)

        return ticket
    }

    fun getAllTickets(): List<Ticket> {
        return ticketRepository.findAll()
    }
}
