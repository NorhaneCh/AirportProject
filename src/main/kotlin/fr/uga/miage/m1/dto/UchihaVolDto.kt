package fr.uga.miage.m1.dto

data class UchihaVolDTO(
    val id: Long,
    val numero: String,
    val origine: String,
    val destination: String,
    val horairePrevuDepart: String,
    val horairePrevuArrivee: String,
    val statutCourant: String,
    val avionImmatriculation: String?,
    val tprevu: String?,
    val tenAttente: String?,
    val tembarquement: String?,
    val tdecolle: String?,
    val tenVol: String?,
    val tarrive: String?,
    val tannule: String?
)
