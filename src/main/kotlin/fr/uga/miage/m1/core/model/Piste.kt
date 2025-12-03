package fr.uga.miage.m1.core.model

data class Piste(
    val id: Long = 0,
    val longueur: Int,
    var etat: EtatPiste = EtatPiste.LIBRE
)

enum class EtatPiste {
    LIBRE,
    OCCUPEE,
    EN_MAINTENANCE
}