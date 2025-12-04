package fr.uga.miage.m1.core.service

import fr.uga.miage.m1.core.model.Piste
import fr.uga.miage.m1.core.port.PisteRepositoryPort
import org.springframework.stereotype.Service

@Service

class PisteService(private val pisteRepository: PisteRepositoryPort) {

    fun createPiste(piste: Piste): Piste = pisteRepository.save(piste)

    fun getPiste(id: Long): Piste? = pisteRepository.findById(id)

    fun getAllPistes(): List<Piste> = pisteRepository.findAll()

    fun updatePiste(piste: Piste): Piste = pisteRepository.update(piste)

    fun deletePiste(id: Long) = pisteRepository.delete(id)
}