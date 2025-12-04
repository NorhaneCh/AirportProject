package fr.uga.miage.m1.dto

import fr.uga.miage.m1.core.model.EtatAvion

data class AvionDto(
    val immatriculation: String,
    val type: String,
    val capacite: Int,
    val etat: EtatAvion
)