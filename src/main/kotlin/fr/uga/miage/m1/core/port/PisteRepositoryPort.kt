package fr.uga.miage.m1.core.port

import fr.uga.miage.m1.core.model.Piste

interface PisteRepositoryPort {
    fun save(piste: Piste): Piste
    fun findById(id: Long): Piste?
    fun findAll(): List<Piste>
    fun update(piste: Piste): Piste
    fun delete(id: Long)
}
