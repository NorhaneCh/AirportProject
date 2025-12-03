package fr.uga.miage.m1.dto

import fr.uga.miage.m1.core.model.Avion

object AvionMapper {

    fun toDto(avion: Avion): AvionDto =
        AvionDto(
            immatriculation = avion.immatriculation,
            type = avion.type,
            capacite = avion.capacite,
            etat = avion.etat
        )

    fun fromDto(dto: AvionDto): Avion =
        Avion(
            immatriculation = dto.immatriculation,
            type = dto.type,
            capacite = dto.capacite,
            etat = dto.etat
        )
}
