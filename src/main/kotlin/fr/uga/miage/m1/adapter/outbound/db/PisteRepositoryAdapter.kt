package fr.uga.miage.m1.adapter.outbound.db

import fr.uga.miage.m1.adapter.outbound.db.entity.toDomain
import fr.uga.miage.m1.adapter.outbound.db.entity.toEntity
import fr.uga.miage.m1.core.model.Piste
import fr.uga.miage.m1.core.port.PisteRepositoryPort
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class PisteRepositoryAdapter(private val repository: JpaPisteRepository) : PisteRepositoryPort {

    override fun save(piste: Piste): Piste =
        repository.save(piste.toEntity()).toDomain()

    override fun findById(id: Long): Piste? =
        repository.findById(id).orElse(null)?.toDomain()

    override fun findAll(): List<Piste> =
        repository.findAll().map { it.toDomain() }

    override fun update(piste: Piste): Piste =
        repository.save(piste.toEntity()).toDomain()

    override fun delete(id: Long) =
        repository.deleteById(id)
}
