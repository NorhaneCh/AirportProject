package fr.uga.miage.m1.core.service.proxy

import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.model.StatutVol
import fr.uga.miage.m1.core.model.Vol
import fr.uga.miage.m1.core.service.VolService
import org.springframework.stereotype.Service

@Service
class VolServiceProxy(
    private val realService: VolService,
    private val userRole: String = "ADMIN"
) {
    private fun checkAdmin() {
        if (userRole != "ADMIN") throw IllegalAccessException("Vous n'avez pas la permission")
    }

    fun creerVol(vol: Vol): Vol {
        checkAdmin()
        return realService.creerVol(vol)
    }

    fun recupererVolParId(id: Long): Vol? = realService.recupererVolParId(id)

    fun modifierVol(vol: Vol): Vol {
        checkAdmin()
        return realService.modifierVol(vol)
    }

    fun supprimerVol(id: Long) {
        checkAdmin()
        realService.supprimerVol(id)
    }

    fun changerStatutVol(id: Long, nouveauStatut: StatutVol): Vol {
        checkAdmin()
        return realService.changerStatutVol(id, nouveauStatut)
    }

    fun assignerAvion(volId: Long, immatriculation: String): Vol {
        checkAdmin()
        return realService.assignerAvion(volId, immatriculation)
    }

    fun assignerPiste(volId: Long, pisteId: Long): Vol {
        checkAdmin()
        return realService.assignerPiste(volId, pisteId)
    }

    fun listerTousLesVols(): List<Vol> = realService.listerTousLesVols()
}

