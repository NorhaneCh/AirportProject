package fr.uga.miage.m1.adapter.outbound.db.mapper

import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.adapter.outbound.db.entity.AvionEntity

object AvionEntityMapper {

    fun toEntity(avion: Avion) = AvionEntity(
        immatriculation = avion.immatriculation,
        type = avion.type,
        capacite = avion.capacite,
        etat = avion.etat
    )

    fun toDomain(entity: AvionEntity) = Avion(
        immatriculation = entity.immatriculation,
        type = entity.type,
        capacite = entity.capacite,
        etat = entity.etat
    )
}
