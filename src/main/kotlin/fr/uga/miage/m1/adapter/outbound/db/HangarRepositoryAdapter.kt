package fr.uga.miage.m1.adapter.outbound.db
import fr.uga.miage.m1.core.model.Hangar
import fr.uga.miage.m1.adapter.outbound.db.mapper.toDomain
import fr.uga.miage.m1.adapter.outbound.db.mapper.toEntity
import fr.uga.miage.m1.core.port.HangarRepositoryPort
import org.springframework.stereotype.Component

@Component
class HangarRepositoryAdapter(private val repository: JpaHangarRepository) : HangarRepositoryPort {

    override fun save(hangar: Hangar): Hangar =
        repository.save(hangar.toEntity()).toDomain()

    override fun findById(id: Long): Hangar? =
        repository.findById(id).orElse(null)?.toDomain()

    override fun findAll(): List<Hangar> =
        repository.findAll().map { it.toDomain() }

    override fun update(hangar: Hangar): Hangar =
        repository.save(hangar.toEntity()).toDomain()

    override fun delete(id: Long) =
        repository.deleteById(id)
}
