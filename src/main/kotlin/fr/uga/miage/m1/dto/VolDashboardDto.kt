package fr.uga.miage.m1.dto

import fr.uga.miage.m1.core.model.StatutVol
import java.time.LocalDateTime

data class VolDashboardDto(
    val id: Long,
    val numero: String,
    val origine: String,
    val destination: String,
    val dateDepart: LocalDateTime,
    val dateArrivee: LocalDateTime,
    val statut: StatutVol,
    val avionId: String?,
    val pisteId: Long?,
    val historiqueStatuts: List<StatutVol>
)
