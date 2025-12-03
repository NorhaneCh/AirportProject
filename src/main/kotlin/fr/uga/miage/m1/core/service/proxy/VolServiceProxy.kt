package fr.uga.miage.m1.core.service.proxy

import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.model.StatutVol
import fr.uga.miage.m1.core.model.Vol
import fr.uga.miage.m1.core.service.VolService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class VolServiceProxy(
    private val realService: VolService,
    @Value("\${app.user.role}") private val userRole: String
) {

    private fun checkAdmin() {
        if (userRole != "ADMIN") {
            throw IllegalAccessException("Vous n'avez pas la permission")
        }
    }

    fun creerVol(vol: Vol): Vol {
        println("LOG: Création du vol ${vol.numero}")
        checkAdmin()
        return realService.creerVol(vol)
    }

    fun recupererVolParId(id: Long): Vol? {
        println("LOG: Récupération du vol id=$id")
        return realService.recupererVolParId(id)
    }

    fun modifierVol(vol: Vol): Vol {
        println("LOG: Modification du vol id=${vol.id}")
        checkAdmin()
        return realService.modifierVol(vol)
    }

    fun supprimerVol(id: Long) {
        println("LOG: Suppression du vol id=$id")
        checkAdmin()
        realService.supprimerVol(id)
    }

    fun changerStatutVol(id: Long, nouveauStatut: StatutVol): Vol {
        println("LOG: Changement du statut du vol id=$id vers $nouveauStatut")
        checkAdmin()
        return realService.changerStatutVol(id, nouveauStatut)
    }

    fun assignerAvion(volId: Long, avion: Avion): Vol {
        println("LOG: Assignation de l'avion ${avion.immatriculation} au vol $volId")
        checkAdmin()
        return realService.assignerAvion(volId, avion.immatriculation)
    }

    // ✅ Nouvelles méthodes pour les pistes
    fun assignerPiste(volId: Long, pisteId: Long): Vol {
        println("LOG: Assignation de la piste $pisteId au vol $volId")
        checkAdmin()
        return realService.assignerPiste(volId, pisteId)
    }

    fun libererPiste(volId: Long): Vol {
        println("LOG: Libération de la piste du vol $volId")
        checkAdmin()
        return realService.libererPiste(volId)
    }

    fun listerVolsParStatut(statut: StatutVol): Collection<Vol> {
        println("LOG: Liste des vols avec statut $statut")
        return realService.listerVolsParStatut(statut)
    }

    fun listerTousLesVols(): Collection<Vol> {
        println("LOG: Liste de tous les vols")
        return realService.listerTousLesVols()
    }

    fun recupererAvionParVol(volId: Long): Avion? {
        println("LOG: Récupération de l'avion du vol $volId")
        return realService.recupererAvionParVol(volId)
    }

    fun historiqueStatutsVol(volId: Long): List<StatutVol> {
        println("LOG: Historique des statuts du vol $volId")
        return realService.historiqueStatutsVol(volId)
    }
}
