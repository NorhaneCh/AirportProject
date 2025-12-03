package fr.uga.miage.m1.adapter.outbound.db

import fr.uga.miage.m1.adapter.outbound.db.entity.VolEntity
import fr.uga.miage.m1.core.model.StatutVol
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaVolRepository : JpaRepository<VolEntity, Long> {

    fun findByNumero(numero: String): VolEntity?

    fun findAllByStatut(statut: StatutVol): Collection<VolEntity>

    fun findAllByOrigine(origine: String): Collection<VolEntity>

    fun findAllByDestination(destination: String): Collection<VolEntity>
}
