package com.pucetec.exam2.models

import jakarta.persistence.*

@Entity
@Table(name = "parkings")
data class Parking(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val level: Int,

    @Column
    val total_capacity: Int = 5,

    @Column
    var occupied_spots: Int = 0,

    @OneToMany(mappedBy = "assignedFloor", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val tickets: List<Ticket> = emptyList()
) {

    fun hasAvailableSpots(): Boolean {
        return occupied_spots < total_capacity
    }

    fun addTicket(ticket: Ticket) {
        this.occupied_spots += 1
    }
}
