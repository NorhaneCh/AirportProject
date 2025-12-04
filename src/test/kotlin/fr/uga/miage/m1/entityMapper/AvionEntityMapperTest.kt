package fr.uga.miage.m1.entityMapper
import fr.uga.miage.m1.adapter.outbound.db.mapper.AvionEntityMapper
import fr.uga.miage.m1.adapter.outbound.db.entity.AvionEntity
import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.model.EtatAvion
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AvionEntityMapperTest {

    @Test
    fun `test mapping domain to entity`() {
        val avion = Avion("F-GAAA", "Boeing 737", 180, EtatAvion.DISPONIBLE)
        val entity = AvionEntityMapper.toEntity(avion)

        assertEquals(avion.immatriculation, entity.immatriculation)
        assertEquals(avion.type, entity.type)
        assertEquals(avion.capacite, entity.capacite)
        assertEquals(avion.etat, entity.etat)
    }

    @Test
    fun `test mapping entity to domain`() {
        val entity = AvionEntity("F-GAAA", "Boeing 737", 180, EtatAvion.EN_VOL)
        val avion = AvionEntityMapper.toDomain(entity)

        assertEquals(entity.immatriculation, avion.immatriculation)
        assertEquals(entity.type, avion.type)
        assertEquals(entity.capacite, avion.capacite)
        assertEquals(entity.etat, avion.etat)
    }
}
