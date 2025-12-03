package fr.uga.miage.m1.dto.mapper

import fr.uga.miage.m1.core.model.Vol
import fr.uga.miage.m1.dto.VolDto

object VolMapper {

    fun toDto(vol: Vol): VolDto = VolDto(
        id = vol.id,
        numero = vol.numero,
        origine = vol.origine,
        destination = vol.destination,
        dateDepart = vol.dateDepart,
        dateArrivee = vol.dateArrivee,
        statut = vol.statut,
        avionId = vol.avionAssigne?.immatriculation,
        pisteId = vol.pisteAssignee?.id,
        historiqueStatuts = vol.historiqueStatuts.toList(),
        isExternal = vol.isExternal

    )

    fun fromDto(dto: VolDto): Vol = Vol(
        id = dto.id ?: 0,
        numero = dto.numero,
        origine = dto.origine,
        destination = dto.destination,
        dateDepart = dto.dateDepart,
        dateArrivee = dto.dateArrivee,
        statut = dto.statut,
        avionAssigne = null,
        pisteAssignee = null,
        historiqueStatuts = dto.historiqueStatuts.toMutableList(),
        isExternal = dto.isExternal
    )

}
