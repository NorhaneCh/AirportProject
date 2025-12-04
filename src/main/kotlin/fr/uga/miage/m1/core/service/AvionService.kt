package fr.uga.miage.m1.core.service

import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.port.AvionRepositoryPort
import org.springframework.stereotype.Service

@Service
class AvionService(private val avionRepository: AvionRepositoryPort) {

    fun createAvion(avion: Avion): Avion =
        avionRepository.save(avion)

    fun getAvion(immatriculation: String): Avion? =
        avionRepository.findById(immatriculation)

    fun getAllAvions(): List<Avion> =
        avionRepository.findAll()

    fun updateAvion(avion: Avion): Avion =
        avionRepository.update(avion)

    fun deleteAvion(immatriculation: String) =
        avionRepository.delete(immatriculation)
}