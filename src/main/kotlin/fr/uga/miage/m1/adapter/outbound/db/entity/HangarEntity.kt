package fr.uga.miage.m1.adapter.outbound.db.entity

import jakarta.persistence.*
import fr.uga.miage.m1.core.model.EtatHangar

@Entity
@Table(name = "hangar")
data class HangarEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val capacite: Int = 0,

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    var etat: EtatHangar = EtatHangar.VIDE,

    @ManyToMany
    @JoinTable(
        name = "hangar_avion",
        joinColumns = [JoinColumn(name = "hangar_id")],
        inverseJoinColumns = [JoinColumn(name = "avion_immatriculation")]
    )
    val avions: MutableList<AvionEntity> = mutableListOf()
)

