package fr.uga.miage.m1.adapter.outbound.db.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import fr.uga.miage.m1.core.model.EtatAvion

@Entity
@Table(name = "avion")
data class AvionEntity(

    @Id
    @Column(nullable = false, unique = true, length = 20)
    var immatriculation: String,

    @Column(length = 50, nullable = false)
    var type: String,

    @Column(nullable = false)
    var capacite: Int,

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    var etat: EtatAvion,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(
        immatriculation = "",
        type = "",
        capacite = 0,
        etat = EtatAvion.DISPONIBLE,
        createdAt = LocalDateTime.now()
    )
}