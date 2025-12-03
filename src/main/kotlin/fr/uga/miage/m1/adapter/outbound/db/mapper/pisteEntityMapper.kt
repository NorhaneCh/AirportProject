package fr.uga.miage.m1.adapter.outbound.db.mapper
import fr.uga.miage.m1.adapter.outbound.db.entity.PisteEntity
import fr.uga.miage.m1.core.model.Piste

class pisteEntityMapper {
    fun PisteEntity.toDomain() = Piste(
        id = this.id ?: 0L,
        longueur = this.longueur,
        etat = this.etat
    )

    fun Piste.toEntity() = PisteEntity(
        id = this.id,
        longueur = this.longueur,
        etat = this.etat
    )

}