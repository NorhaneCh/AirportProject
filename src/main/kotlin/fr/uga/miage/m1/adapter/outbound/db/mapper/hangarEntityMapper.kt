package fr.uga.miage.m1.adapter.outbound.db.mapper

import fr.uga.miage.m1.adapter.outbound.db.entity.*
import fr.uga.miage.m1.core.model.*

fun HangarEntity.toDomain(): Hangar = Hangar(
    id = this.id ?: 0L,
    capacite = this.capacite,
    etat = this.etat,
    avions = this.avions.map { it.toDomain() }.toMutableList()
)

fun Hangar.toEntity(): HangarEntity = HangarEntity(
    id = this.id,
    capacite = this.capacite,
    etat = this.etat,
    avions = this.avions.map { it.toEntity() }.toMutableList()
)

// Avion mapping
fun AvionEntity.toDomain(): Avion = Avion(
    immatriculation = this.immatriculation,
    type = this.type,
    capacite = this.capacite,
    etat = this.etat
)

fun Avion.toEntity(): AvionEntity = AvionEntity(
    immatriculation = this.immatriculation,
    type = this.type,
    capacite = this.capacite,
    etat = this.etat
)