package fr.uga.miage.m1.dto.mapper

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import fr.uga.miage.m1.dto.UchihaVolDTO
import fr.uga.miage.m1.core.model.Vol
import fr.uga.miage.m1.core.model.StatutVol

object UchihaMapper {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    // --- From DTO to Domain ---
    fun fromDto(dto: UchihaVolDTO): Vol {
        val statut = StatutVol.valueOf(dto.statutCourant)

        val historique = mutableListOf<StatutVol>()
        if (dto.tprevu != null) historique.add(StatutVol.PREVU)
        if (dto.tenAttente != null) historique.add(StatutVol.EN_ATTENTE)
        if (dto.tembarquement != null) historique.add(StatutVol.EMBARQUEMENT)
        if (dto.tdecolle != null) historique.add(StatutVol.DECOLLE)
        if (dto.tenVol != null) historique.add(StatutVol.EN_VOL)
        if (dto.tarrive != null) historique.add(StatutVol.ARRIVE)
        if (dto.tannule != null) historique.add(StatutVol.ANNULE)

        return Vol(
            id = dto.id,
            numero = dto.numero,
            origine = dto.origine,
            destination = dto.destination,
            dateDepart = LocalDateTime.parse(dto.horairePrevuDepart),
            dateArrivee = LocalDateTime.parse(dto.horairePrevuArrivee),
            statut = statut,
            historiqueStatuts = historique,
            isExternal = true
        )
    }

    // --- From Domain to DTO ---
    fun toDto(vol: Vol): UchihaVolDTO {
        return UchihaVolDTO(
            id = vol.id,
            numero = vol.numero,
            origine = vol.origine,
            destination = vol.destination,
            horairePrevuDepart = vol.dateDepart.format(formatter),
            horairePrevuArrivee = vol.dateArrivee.format(formatter),
            statutCourant = vol.statut.name,
            avionImmatriculation = vol.avionAssigne?.immatriculation ?: "",
            tprevu = if (vol.historiqueStatuts.contains(StatutVol.PREVU)) vol.dateDepart.format(formatter) else null,
            tenAttente = if (vol.historiqueStatuts.contains(StatutVol.EN_ATTENTE)) vol.dateDepart.format(formatter) else null,
            tembarquement = if (vol.historiqueStatuts.contains(StatutVol.EMBARQUEMENT)) vol.dateDepart.format(formatter) else null,
            tdecolle = if (vol.historiqueStatuts.contains(StatutVol.DECOLLE)) vol.dateDepart.format(formatter) else null,
            tenVol = if (vol.historiqueStatuts.contains(StatutVol.EN_VOL)) vol.dateDepart.format(formatter) else null,
            tarrive = if (vol.historiqueStatuts.contains(StatutVol.ARRIVE)) vol.dateArrivee.format(formatter) else null,
            tannule = if (vol.historiqueStatuts.contains(StatutVol.ANNULE)) vol.dateArrivee.format(formatter) else null
        )
    }
}
