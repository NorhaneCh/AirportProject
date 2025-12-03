package fr.uga.miage.m1.core.model

data class Hangar(
    val id: Long = 0,
    val capacite: Int,
    var etat: EtatHangar = EtatHangar.VIDE,
    val avions: MutableList<Avion> = mutableListOf()
)

enum class EtatHangar {
    VIDE,
    PARTIELLEMENT_PLEIN,
    PLEIN,
    EN_MAINTENANCE,
    INDISPONIBLE
}