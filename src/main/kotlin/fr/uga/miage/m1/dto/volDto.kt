package fr.uga.miage.m1.dto

import fr.uga.miage.m1.core.model.StatutVol
import java.time.LocalDateTime

data class VolDto(
    val id: Long?,
    val numero: String,
    val origine: String,
    val destination: String,
    val dateDepart: LocalDateTime,
    val dateArrivee: LocalDateTime,
    val statut: StatutVol,
    val avionId: String? = null,
    val pisteId: Long? = null,
    val historiqueStatuts: List<StatutVol> = emptyList(),
    val isExternal: Boolean = false
)

