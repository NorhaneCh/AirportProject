package fr.uga.miage.m1.adapter.outbound.db

import fr.uga.miage.m1.adapter.outbound.db.entity.toDomain
import fr.uga.miage.m1.adapter.outbound.db.entity.toEntity
import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.port.AvionRepositoryPort
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class AvionRepositoryAdapter(private val repository: JpaAvionRepository) : AvionRepositoryPort {

    override fun save(avion: Avion): Avion =
        repository.save(avion.toEntity()).toDomain()

    override fun findById(immatriculation: String): Avion? =
        repository.findById(immatriculation).orElse(null)?.toDomain()

    override fun findAll(): List<Avion> =
        repository.findAll().map { it.toDomain() }

    override fun update(avion: Avion): Avion =
        repository.save(avion.toEntity()).toDomain()

    override fun delete(immatriculation: String) =
        repository.deleteById(immatriculation)
}
