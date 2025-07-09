package com.pucetec.exam2.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tickets")
data class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id", nullable = false)
    val assignedFloor: Parking,

    @Column(name = "assigned_parking")
    val assignedParking: Int,

    @Column(name = "entry_time")
    val entryTime: LocalDateTime,

    @Column
    var exitTime: LocalDateTime? = null
)
