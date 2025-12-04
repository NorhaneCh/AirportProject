package fr.uga.miage.m1.dto
import fr.uga.miage.m1.core.model.EtatHangar

data class HangarDto(
    val id: Long? = null,
    val capacite: Int,
    val etat: EtatHangar = EtatHangar.VIDE,
    val avions: Collection<AvionDto> = emptyList()
)