package fr.uga.miage.m1.core.factory

import fr.uga.miage.m1.core.model.Vol
import fr.uga.miage.m1.core.model.StatutVol
import java.time.LocalDateTime

object VolFactory {

    fun creerVol(
        numero: String,
        origine: String,
        destination: String,
        dateDepart: LocalDateTime,
        dateArrivee: LocalDateTime
    ): Vol {
        require(numero.isNotBlank()) { "Le numéro de vol est obligatoire" }
        require(origine.isNotBlank()) { "L'origine est obligatoire" }
        require(destination.isNotBlank()) { "La destination est obligatoire" }
        require(dateArrivee.isAfter(dateDepart)) { "La date d'arrivée doit être après la date de départ" }

        return Vol(
            id = 0,
            numero = numero,
            origine = origine,
            destination = destination,
            dateDepart = dateDepart,
            dateArrivee = dateArrivee,
            statut = StatutVol.PREVU,
            historiqueStatuts = mutableListOf(StatutVol.PREVU)
        )
    }
}
