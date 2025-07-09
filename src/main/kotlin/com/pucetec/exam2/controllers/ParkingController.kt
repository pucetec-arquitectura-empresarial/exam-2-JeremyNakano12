package com.pucetec.exam2.controllers

import com.pucetec.exam2.services.ParkingService
import com.pucetec.exam2.services.TicketService
import com.pucetec.exam2.models.Parking
import com.pucetec.exam2.models.Ticket
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/parkings")
class ParkingController(
    private val parkingService: ParkingService,
    private val ticketService: TicketService
) {

    @GetMapping("/availability")
    fun checkAvailability(): ResponseEntity<String> {
        val availableParking = parkingService.getAllParkings().firstOrNull { it.hasAvailableSpots() }

        return if (availableParking != null) {
            ResponseEntity.ok("Available parking on floor ${availableParking.level}")
        } else {
            ResponseEntity.status(404).body("No available parkings.")
        }
    }

    @PostMapping("/entry")
    fun registerEntry(): ResponseEntity<Ticket> {
        val ticket = ticketService.addTicket()
        return ResponseEntity.ok(ticket)
    }

    @PostMapping("/exit/{ticketId}")
    fun registerExit(@PathVariable ticketId: Long): ResponseEntity<String> {
        val ticket = ticketService.getAllTickets().find { it.id == ticketId }
        return if (ticket != null) {
            ticket.exitTime = LocalDateTime.now()
            ticketService.addTicket()
            ResponseEntity.ok("Exit registered for ticket ID: $ticketId")
        } else {
            ResponseEntity.status(404).body("Ticket not found.")
        }
    }
}
