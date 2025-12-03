package fr.uga.miage.m1.core.model
import java.time.LocalDateTime

data class Vol(
    val id: Long,
    val numero: String,
    val origine: String,
    val destination: String,
    val dateDepart: LocalDateTime,
    val dateArrivee: LocalDateTime,
    var statut: StatutVol = StatutVol.PREVU,
    var avionAssigne: Avion? = null,
    var pisteAssignee: Piste? = null,
    val historiqueStatuts: MutableList<StatutVol> = mutableListOf(StatutVol.PREVU),
    var isExternal: Boolean = false
) {

    fun changerStatut(nouveauStatut: StatutVol) {
        if (this.statut == StatutVol.ANNULE) {
            throw IllegalStateException("Impossible de modifier le statut d’un vol annulé.")
        }
        this.statut = nouveauStatut
        historiqueStatuts.add(nouveauStatut)
    }

    fun assignerAvion(avion: Avion) {
        this.avionAssigne = avion
    }
}

enum class StatutVol {
    PREVU,
    EN_ATTENTE,
    EMBARQUEMENT,
    DECOLLE,
    EN_VOL,
    ARRIVE,
    ANNULE
}
