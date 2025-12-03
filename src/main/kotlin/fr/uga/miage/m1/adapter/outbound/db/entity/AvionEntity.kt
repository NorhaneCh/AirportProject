package fr.uga.miage.m1.adapter.outbound.db.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import fr.uga.miage.m1.core.model.EtatAvion
import fr.uga.miage.m1.core.model.Avion

@Entity
@Table(name = "avion")
data class AvionEntity(
    @Id
    @Column(nullable = false, unique = true, length = 20)
    val immatriculation: String,

    @Column(length = 50, nullable = false)
    val type: String,

    @Column(nullable = false)
    val capacite: Int,

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    var etat: EtatAvion = EtatAvion.DISPONIBLE,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)


