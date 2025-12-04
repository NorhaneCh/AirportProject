package fr.uga.miage.m1.core.service

import fr.uga.miage.m1.core.model.Vol
import fr.uga.miage.m1.core.model.StatutVol
import fr.uga.miage.m1.core.model.EtatPiste
import fr.uga.miage.m1.core.port.AvionRepositoryPort
import fr.uga.miage.m1.core.port.VolRepositoryPort
import fr.uga.miage.m1.core.port.PisteRepositoryPort
import org.springframework.stereotype.Service

@Service
class VolService(
    private val volRepository: VolRepositoryPort
) {

    private fun getVolOrThrow(id: Long): Vol =
        volRepository.findById(id) ?: throw IllegalArgumentException("Vol introuvable")

    fun creerVol(vol: Vol): Vol {
        return volRepository.save(vol)
    }

    fun recupererVolParId(id: Long): Vol? = volRepository.findById(id)

    fun modifierVol(vol: Vol): Vol {
        getVolOrThrow(vol.id)
        return volRepository.save(vol)
    }

    fun supprimerVol(id: Long) = volRepository.deleteById(id)

    fun changerStatutVol(id: Long, nouveauStatut: StatutVol): Vol {
        val vol = getVolOrThrow(id)
        vol.changerStatut(nouveauStatut)
        return volRepository.save(vol)
    }

    fun assignerAvion(volId: Long, immatriculation: String): Vol {
        val vol = getVolOrThrow(volId)
        vol.avionImmatriculation = immatriculation
        return volRepository.save(vol)
    }

    fun assignerPiste(volId: Long, pisteId: Long): Vol {
        val vol = getVolOrThrow(volId)
        vol.pisteId = pisteId
        return volRepository.save(vol)
    }

    fun listerTousLesVols(): List<Vol> = volRepository.findAll() as List<Vol>
}
