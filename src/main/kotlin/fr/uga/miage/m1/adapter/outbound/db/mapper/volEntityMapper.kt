package fr.uga.miage.m1.adapter.outbound.db.entity

import fr.uga.miage.m1.core.model.Vol
import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.model.Piste
import fr.uga.miage.m1.core.model.StatutVol

fun VolEntity.toDomain() = Vol(
    id = this.id ?: 0,
    numero = this.numero,
    origine = this.origine,
    destination = this.destination,
    dateDepart = this.dateDepart,
    dateArrivee = this.dateArrivee,
    statut = this.statut,
    avionImmatriculation = this.avionImmatriculation,
    pisteId = this.pisteId,
    historiqueStatuts = this.historiqueStatuts.toMutableList(),
    isExternal = this.isExternal
)

fun Vol.toEntityForCreate() = VolEntity(
    numero = this.numero,
    origine = this.origine,
    destination = this.destination,
    dateDepart = this.dateDepart,
    dateArrivee = this.dateArrivee,
    statut = this.statut,
    avionImmatriculation = this.avionImmatriculation,
    pisteId = this.pisteId,
    historiqueStatuts = this.historiqueStatuts.toMutableList(),
    isExternal = this.isExternal
)

fun Vol.toEntityForUpdate() = VolEntity(
    id = this.id,
    numero = this.numero,
    origine = this.origine,
    destination = this.destination,
    dateDepart = this.dateDepart,
    dateArrivee = this.dateArrivee,
    statut = this.statut,
    avionImmatriculation = this.avionImmatriculation,
    pisteId = this.pisteId,
    historiqueStatuts = this.historiqueStatuts.toMutableList(),
    isExternal = this.isExternal
)

