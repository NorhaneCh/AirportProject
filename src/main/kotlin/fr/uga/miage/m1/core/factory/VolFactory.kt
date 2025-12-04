package fr.uga.miage.m1.core.factory

import fr.uga.miage.m1.core.model.Vol
import fr.uga.miage.m1.core.model.StatutVol
import java.time.LocalDateTime

object VolFactory {

    fun creerVol(
        numero: String? = null,
        origine: String,
        destination: String,
        dateDepart: LocalDateTime,
        dateArrivee: LocalDateTime,
        avionImmatriculation: String? = null,
        pisteId: Long? = null
    ): Vol {
        require(origine.isNotBlank()) { "L'origine est obligatoire" }
        require(destination.isNotBlank()) { "La destination est obligatoire" }
        require(dateArrivee.isAfter(dateDepart)) { "La date d'arrivée doit être après la date de départ" }

        val numeroGenere = numero ?: generateFlightNumber()

        return Vol(
            id = 0,
            numero = numeroGenere,
            origine = origine,
            destination = destination,
            dateDepart = dateDepart,
            dateArrivee = dateArrivee,
            statut = StatutVol.PREVU,
            avionImmatriculation = avionImmatriculation,
            pisteId = pisteId,
            historiqueStatuts = mutableListOf(StatutVol.PREVU)
        )
    }

    private fun generateFlightNumber(): String {
        return "V${System.currentTimeMillis() % 1000000}"
    }
}
