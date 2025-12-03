package fr.uga.miage.m1.core.service

import fr.uga.miage.m1.core.factory.VolFactory
import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.model.EtatPiste
import fr.uga.miage.m1.core.model.Vol
import fr.uga.miage.m1.core.model.StatutVol
import fr.uga.miage.m1.core.port.AvionRepositoryPort
import fr.uga.miage.m1.core.port.VolRepositoryPort
import fr.uga.miage.m1.core.port.PisteRepositoryPort
import java.util.Optional
import org.springframework.stereotype.Service

@Service
class VolService(
    private val volRepository: VolRepositoryPort,
    private val avionRepository: AvionRepositoryPort,
    private val pisteRepository: PisteRepositoryPort,
    private val pisteService: PisteService
) {

    companion object {
        private const val VOL_INTROUVABLE = "Vol introuvable"
    }

    private fun getVolOrThrow(id: Long): Vol =
        volRepository.findById(id)?: throw IllegalArgumentException(VOL_INTROUVABLE)

    fun creerVol(vol: Vol): Vol {
        val volCree = VolFactory.creerVol(
            numero = vol.numero,
            origine = vol.origine,
            destination = vol.destination,
            dateDepart = vol.dateDepart,
            dateArrivee = vol.dateArrivee
        )
        return volRepository.save(volCree)
    }

    fun recupererVolParId(id: Long): Vol? =
        volRepository.findById(id)

    fun recupererVolParNumero(numero: String): Vol? =
        volRepository.findByNumero(numero)

    fun modifierVol(vol: Vol): Vol {
        getVolOrThrow(vol.id ?: throw IllegalArgumentException("Vol sans id"))
        return volRepository.save(vol)
    }

    fun supprimerVol(id: Long) {
        volRepository.deleteById(id)
    }

    fun changerStatutVol(id: Long, nouveauStatut: StatutVol): Vol {
        val vol = getVolOrThrow(id)
        vol.changerStatut(nouveauStatut)
        return volRepository.save(vol)
    }

    fun listerVolsParStatut(statut: StatutVol): Collection<Vol> =
        volRepository.findAllByStatut(statut)

    fun listerTousLesVols(): Collection<Vol> = volRepository.findAll()

    fun recupererAvionParVol(volId: Long): Avion? = getVolOrThrow(volId).avionAssigne

    fun historiqueStatutsVol(volId: Long): List<StatutVol> = getVolOrThrow(volId).historiqueStatuts

    fun assignerAvion(volId: Long, immatriculationAvion: String): Vol {
        val vol = getVolOrThrow(volId)

        val avion = avionRepository.findById(immatriculationAvion)
            ?: throw IllegalArgumentException("Avion introuvable : $immatriculationAvion")

        vol.avionAssigne = avion
        return volRepository.save(vol)
    }

    //Gestion Pistes
    fun assignerPiste(volId: Long, pisteId: Long): Vol {
        val vol = getVolOrThrow(volId)
        val piste = pisteService.getPiste(pisteId)
            ?: throw IllegalArgumentException("Piste introuvable : $pisteId")

        if (piste.etat != EtatPiste.LIBRE) {
            throw IllegalStateException("Piste $pisteId non disponible")
        }

        piste.etat = EtatPiste.OCCUPEE
        pisteRepository.save(piste)

        vol.pisteAssignee = piste
        return volRepository.save(vol)
    }


    fun libererPiste(volId: Long): Vol {
        val vol = getVolOrThrow(volId)
        val piste = vol.pisteAssignee ?: return vol  // rien à libérer

        piste.etat = EtatPiste.LIBRE
        pisteRepository.save(piste)

        vol.pisteAssignee = null
        return volRepository.save(vol)
    }
    fun listerVolsDepart(origine: String): List<Vol> =
        volRepository.findAll().filter { it.origine == origine && it.statut != StatutVol.ANNULE }

    fun listerVolsArrivee(destination: String): List<Vol> =
        volRepository.findAll().filter { it.destination == destination && it.statut != StatutVol.ANNULE }
}
