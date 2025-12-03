package fr.uga.miage.m1.dto

import fr.uga.miage.m1.core.model.Piste

object PisteMapper {
    fun toDto(piste: Piste): PisteDto = PisteDto(
        id = piste.id,
        longueur = piste.longueur,
        etat = piste.etat
    )

    fun fromDto(dto: PisteDto): Piste = Piste(
        id = dto.id,
        longueur = dto.longueur,
        etat = dto.etat
    )
}
