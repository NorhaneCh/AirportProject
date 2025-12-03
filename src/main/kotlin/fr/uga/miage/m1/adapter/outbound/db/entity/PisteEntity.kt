package fr.uga.miage.m1.adapter.outbound.db.entity

import fr.uga.miage.m1.core.model.EtatPiste
import jakarta.persistence.*
import fr.uga.miage.m1.core.model.Piste

@Entity
@Table(name = "piste")
data class PisteEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val longueur: Int,

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    var etat: EtatPiste,
)
{
    constructor() : this(0, 0, EtatPiste.LIBRE)
}

