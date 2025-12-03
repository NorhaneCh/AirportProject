package fr.uga.miage.m1.core.port

import fr.uga.miage.m1.core.model.Avion

interface AvionRepositoryPort {
    fun save(avion: Avion): Avion
    fun findById(immatriculation: String): Avion?
    fun findAll(): List<Avion>
    fun update(avion: Avion): Avion
    fun delete(immatriculation: String)
}
