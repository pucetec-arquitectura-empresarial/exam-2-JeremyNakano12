package com.pucetec.exam2.repositories

import com.pucetec.exam2.models.Parking
import com.pucetec.exam2.models.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository: JpaRepository<Ticket, Long>