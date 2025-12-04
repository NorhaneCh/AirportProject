package fr.uga.miage.m1.dto.mapper

import fr.uga.miage.m1.core.model.Vol
import fr.uga.miage.m1.dto.VolDashboardDto

object VolDashboardMapper {

    fun toDto(vol: Vol): VolDashboardDto = VolDashboardDto(
        id = vol.id,
        numero = vol.numero,
        origine = vol.origine,
        destination = vol.destination,
        dateDepart = vol.dateDepart,
        dateArrivee = vol.dateArrivee,
        statut = vol.statut,
        avionId = vol.avionImmatriculation,
        pisteId = vol.pisteId,
        historiqueStatuts = vol.historiqueStatuts.toList()
    )
}
