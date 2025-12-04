package fr.uga.miage.m1.adapter.outbound.db

import fr.uga.miage.m1.core.model.StatutVol
import fr.uga.miage.m1.core.model.Vol
import fr.uga.miage.m1.core.port.VolRepositoryPort
import org.springframework.stereotype.Service
import fr.uga.miage.m1.adapter.outbound.db.entity.toEntityForCreate
import fr.uga.miage.m1.adapter.outbound.db.entity.toEntityForUpdate
import fr.uga.miage.m1.adapter.outbound.db.entity.toDomain
import org.springframework.stereotype.Component
import java.util.*

@Component
class VolRepositoryAdapter(private val repository: JpaVolRepository) : VolRepositoryPort {

    override fun save(vol: Vol): Vol {
        val entity = if (vol.id == null || vol.id == 0L) {
            vol.toEntityForCreate()
        } else {
            val existing = repository.findById(vol.id)
            if (existing.isPresent) {
                val updated = vol.toEntityForUpdate()
                updated.id = existing.get().id
                updated
            } else {
                vol.toEntityForCreate()
            }
        }
        val savedEntity = repository.save(entity)
        return savedEntity.toDomain()
    }

    override fun findById(id: Long): Vol? =
        repository.findById(id).orElse(null)?.toDomain()

    override fun findByNumero(numero: String): Vol? =
        repository.findByNumero(numero)?.toDomain()
    override fun deleteById(id: Long) = repository.deleteById(id)

    override fun findAllByStatut(statut: StatutVol): List<Vol> =
        repository.findAllByStatut(statut).map { it.toDomain() }

    override fun findAll(): List<Vol> =
        repository.findAll().map { it.toDomain() }
}
