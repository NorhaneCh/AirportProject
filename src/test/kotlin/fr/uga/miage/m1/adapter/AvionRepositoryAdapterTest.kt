package fr.uga.miage.m1.adapter
import fr.uga.miage.m1.adapter.outbound.db.AvionRepositoryAdapter
import fr.uga.miage.m1.adapter.outbound.db.JpaAvionRepository
import fr.uga.miage.m1.adapter.outbound.db.entity.AvionEntity
import fr.uga.miage.m1.adapter.outbound.db.mapper.AvionEntityMapper
import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.model.EtatAvion
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import java.util.*

class AvionRepositoryAdapterTest {

    private val jpaRepo: JpaAvionRepository = mockk()
    private val adapter = AvionRepositoryAdapter(jpaRepo)

    @Test
    fun `save should convert domain to entity and back`() {
        val avion = Avion("F-GAAA", "B737", 180, EtatAvion.DISPONIBLE)
        every { jpaRepo.save(any<AvionEntity>()) } answers { firstArg() }

        val result = adapter.save(avion)

        assertEquals(avion.immatriculation, result.immatriculation)
        assertEquals(avion.type, result.type)
        assertEquals(avion.capacite, result.capacite)
        assertEquals(avion.etat, result.etat)

        verify { jpaRepo.save(any<AvionEntity>()) }
    }


    @Test
    fun `findById returns domain object when present`() {
        val entity = AvionEntity("F-GAAA", "B737", 180, EtatAvion.EN_VOL)
        every { jpaRepo.findById("F-GAAA") } returns Optional.of(entity)

        val result = adapter.findById("F-GAAA")

        assertEquals(entity.immatriculation, result?.immatriculation)
        verify { jpaRepo.findById("F-GAAA") }
    }

    @Test
    fun `findById returns null when absent`() {
        every { jpaRepo.findById("F-GAAA") } returns Optional.empty()

        val result = adapter.findById("F-GAAA")

        assertNull(result)
        verify { jpaRepo.findById("F-GAAA") }
    }
    @Test
    fun `findAll returns list of domain objects`() {
        val entities = listOf(
            AvionEntity("F-GAAA", "B737", 180, EtatAvion.DISPONIBLE),
            AvionEntity("F-GBBB", "Airbus A320", 150, EtatAvion.EN_VOL)
        )
        every { jpaRepo.findAll() } returns entities

        val result = adapter.findAll()

        assertEquals(2, result.size)
        assertEquals("F-GAAA", result[0].immatriculation)
        assertEquals("F-GBBB", result[1].immatriculation)
        verify { jpaRepo.findAll() }
    }

    @Test
    fun `update should convert domain to entity and back`() {
        val avion = Avion("F-GAAA", "B737", 180, EtatAvion.DISPONIBLE)
        every { jpaRepo.save(any<AvionEntity>()) } answers { firstArg() }

        val result = adapter.update(avion)

        assertEquals(avion.immatriculation, result.immatriculation)
        assertEquals(avion.type, result.type)
        assertEquals(avion.capacite, result.capacite)
        assertEquals(avion.etat, result.etat)
        verify { jpaRepo.save(any<AvionEntity>()) }
    }

    @Test
    fun `delete should call deleteById on repository`() {
        every { jpaRepo.deleteById("F-GAAA") } returns Unit

        adapter.delete("F-GAAA")

        verify { jpaRepo.deleteById("F-GAAA") }
    }

}
