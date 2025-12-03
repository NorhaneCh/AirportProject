package fr.uga.miage.m1.core.port

import fr.uga.miage.m1.core.model.Hangar

interface HangarRepositoryPort {
    fun save(hangar: Hangar): Hangar
    fun findById(id: Long): Hangar?
    fun findAll(): List<Hangar>
    fun update(hangar: Hangar): Hangar
    fun delete(id: Long)
}
