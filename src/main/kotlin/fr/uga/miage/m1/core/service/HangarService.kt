package fr.uga.miage.m1.core.service

import fr.uga.miage.m1.core.model.Hangar
import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.port.HangarRepositoryPort
import org.springframework.stereotype.Service

@Service

class HangarService(private val hangarRepository: HangarRepositoryPort) {

    fun createHangar(hangar: Hangar): Hangar = hangarRepository.save(hangar)

    fun getHangar(id: Long): Hangar? = hangarRepository.findById(id)

    fun getAllHangars(): List<Hangar> = hangarRepository.findAll()

    fun updateHangar(hangar: Hangar): Hangar = hangarRepository.update(hangar)

    fun deleteHangar(id: Long) = hangarRepository.delete(id)

    fun addAvionToHangar(hangarId: Long, avion: Avion) {
        val hangar = hangarRepository.findById(hangarId) ?: return
        if (hangar.avions.size < hangar.capacite) hangar.avions.add(avion)
        hangarRepository.update(hangar)
    }

    fun removeAvionFromHangar(hangarId: Long, avion: Avion) {
        val hangar = hangarRepository.findById(hangarId) ?: return
        hangar.avions.removeIf { it.immatriculation == avion.immatriculation }
        hangarRepository.update(hangar)
    }
}