package fr.uga.miage.m1.adapter.outbound.db.entity

import fr.uga.miage.m1.core.model.StatutVol
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "vols")
class VolEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var numero: String = "",

    @Column(nullable = false)
    var origine: String = "",

    @Column(nullable = false)
    var destination: String = "",

    @Column(nullable = false)
    var dateDepart: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var dateArrivee: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var statut: StatutVol = StatutVol.PREVU,

    // Relation avec AvionEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avion_immatriculation", referencedColumnName = "immatriculation")
    var avion: AvionEntity? = null,

    // Relation avec PisteEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "piste_id", referencedColumnName = "id")
    var piste: PisteEntity? = null,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "historique_statuts", joinColumns = [JoinColumn(name = "vol_id")])
    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    var historiqueStatuts: MutableList<StatutVol> = mutableListOf(StatutVol.PREVU),

    // ➕ AJOUT POUR SYNCHRO ENTRE AÉROPORTS
    @Column(name = "is_external", nullable = false)
    var isExternal: Boolean = false
)
