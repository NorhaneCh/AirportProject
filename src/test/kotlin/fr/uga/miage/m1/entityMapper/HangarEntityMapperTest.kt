package fr.uga.miage.m1.entityMapper

import fr.uga.miage.m1.adapter.outbound.db.entity.AvionEntity
import fr.uga.miage.m1.adapter.outbound.db.entity.HangarEntity
import fr.uga.miage.m1.adapter.outbound.db.mapper.toDomain
import fr.uga.miage.m1.adapter.outbound.db.mapper.toEntity
import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.model.Hangar
import fr.uga.miage.m1.core.model.EtatAvion
import fr.uga.miage.m1.core.model.EtatHangar
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HangarEntityMapperTest {

    @Test
    fun `test mapping domain to entity with avions`() {
        val avionsDomain = mutableListOf(
            Avion("F-GAAA", "Boeing", 180, EtatAvion.DISPONIBLE),
            Avion("F-GAAB", "Airbus", 150, EtatAvion.EN_VOL)
        )
        val hangarDomain = Hangar(id = 1L, capacite = 2, etat = EtatHangar.PARTIELLEMENT_PLEIN, avions = avionsDomain)
        val entity = hangarDomain.toEntity()

        assertEquals(hangarDomain.id, entity.id)
        assertEquals(hangarDomain.capacite, entity.capacite)
        assertEquals(hangarDomain.etat, entity.etat)
        assertEquals(hangarDomain.avions.size, entity.avions.size)
        assertTrue(entity.avions.any { it.immatriculation == "F-GAAA" })
        assertTrue(entity.avions.any { it.immatriculation == "F-GAAB" })
    }

    @Test
    fun `test mapping entity to domain with avions`() {
        val avionsEntity = mutableListOf(
            AvionEntity("F-GAAA", "Boeing", 180, EtatAvion.DISPONIBLE),
            AvionEntity("F-GAAB", "Airbus", 150, EtatAvion.EN_VOL)
        )
        val hangarEntity = HangarEntity(id = 1L, capacite = 2, etat = EtatHangar.PLEIN, avions = avionsEntity)
        val domain = hangarEntity.toDomain()

        assertEquals(hangarEntity.id ?: 0L, domain.id)
        assertEquals(hangarEntity.capacite, domain.capacite)
        assertEquals(hangarEntity.etat, domain.etat)
        assertEquals(hangarEntity.avions.size, domain.avions.size)
        assertTrue(domain.avions.any { it.immatriculation == "F-GAAA" })
        assertTrue(domain.avions.any { it.immatriculation == "F-GAAB" })
    }
}
