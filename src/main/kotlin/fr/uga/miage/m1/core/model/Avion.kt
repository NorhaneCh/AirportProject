package fr.uga.miage.m1.core.model

data class Avion(
    val immatriculation: String,
    val type: String,
    val capacite: Int,
    var etat: EtatAvion = EtatAvion.DISPONIBLE)

enum class EtatAvion {
    DISPONIBLE,
    EN_VOL,
    EN_MAINTENANCE,
    INDISPONIBLE
}
