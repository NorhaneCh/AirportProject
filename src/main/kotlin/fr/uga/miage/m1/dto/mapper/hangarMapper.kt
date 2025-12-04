package fr.uga.miage.m1.dto

import fr.uga.miage.m1.core.model.Hangar
import fr.uga.miage.m1.core.model.Avion

object HangarMapper {


    fun toDto(hangar: Hangar): HangarDto =
        HangarDto(
            id = hangar.id,
            capacite = hangar.capacite,
            etat = hangar.etat,
            avions = hangar.avions.map { AvionMapper.toDto(it) }
        )


    fun fromDto(dto: HangarDto, avions: Collection<Avion>): Hangar =
        Hangar(
            id = dto.id,
            capacite = dto.capacite,
            etat = dto.etat,
            avions = avions.toMutableList()
        )
}