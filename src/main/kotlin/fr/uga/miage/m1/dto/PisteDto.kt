package fr.uga.miage.m1.dto

import fr.uga.miage.m1.core.model.EtatPiste

data class PisteDto(
    val id: Long,
    val longueur: Int,
    val etat: EtatPiste
)
