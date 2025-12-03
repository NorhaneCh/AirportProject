package fr.uga.miage.m1.adapter.outbound.db.entity

import fr.uga.miage.m1.core.model.EtatPiste
import jakarta.persistence.*

@Entity
@Table(name = "piste")
data class PisteEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val longueur: Int = 0,

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    var etat: EtatPiste = EtatPiste.LIBRE
)