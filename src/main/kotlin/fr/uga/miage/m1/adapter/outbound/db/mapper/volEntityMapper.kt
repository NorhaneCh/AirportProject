package fr.uga.miage.m1.adapter.outbound.db.entity

import fr.uga.miage.m1.core.model.Vol
import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.model.Piste
import fr.uga.miage.m1.core.model.StatutVol

// --- Extensions locales pour Avion/Piste ---
fun Avion.toEntity(): AvionEntity = AvionEntity(
    immatriculation = this.immatriculation,
    type = this.type,
    capacite = this.capacite,
    etat = this.etat
)

fun AvionEntity.toDomain(): Avion = Avion(
    immatriculation = this.immatriculation,
    type = this.type,
    capacite = this.capacite,
    etat = this.etat
)

fun Piste.toEntity(): PisteEntity = PisteEntity(
    id = this.id,
    longueur = this.longueur,
    etat = this.etat
)

fun PisteEntity.toDomain(): Piste = Piste(
    id = this.id,
    longueur = this.longueur,
    etat = this.etat
)

// --- Mapper Vol <-> VolEntity ---
fun Vol.toEntityForCreate() = VolEntity(
    numero = this.numero,
    origine = this.origine,
    destination = this.destination,
    dateDepart = this.dateDepart,
    dateArrivee = this.dateArrivee,
    statut = this.statut,
    avion = this.avionAssigne?.toEntity(),
    piste = this.pisteAssignee?.toEntity(),
    historiqueStatuts = this.historiqueStatuts.toMutableList()
)

fun Vol.toEntityForUpdate() = VolEntity(
    id = this.id,
    numero = this.numero,
    origine = this.origine,
    destination = this.destination,
    dateDepart = this.dateDepart,
    dateArrivee = this.dateArrivee,
    statut = this.statut,
    avion = this.avionAssigne?.toEntity(),
    piste = this.pisteAssignee?.toEntity(),
    historiqueStatuts = this.historiqueStatuts.toMutableList()
)

fun VolEntity.toDomain() = Vol(
    id = this.id ?: 0,
    numero = this.numero,
    origine = this.origine,
    destination = this.destination,
    dateDepart = this.dateDepart,
    dateArrivee = this.dateArrivee,
    statut = this.statut,
    avionAssigne = this.avion?.toDomain(),
    pisteAssignee = this.piste?.toDomain(),
    historiqueStatuts = this.historiqueStatuts.toMutableList()
)
