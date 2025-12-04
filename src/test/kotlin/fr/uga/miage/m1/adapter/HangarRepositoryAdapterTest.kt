package fr.uga.miage.m1.adapter

import fr.uga.miage.m1.adapter.outbound.db.HangarRepositoryAdapter
import fr.uga.miage.m1.adapter.outbound.db.JpaHangarRepository
import fr.uga.miage.m1.adapter.outbound.db.entity.HangarEntity
import fr.uga.miage.m1.adapter.outbound.db.mapper.toDomain
import fr.uga.miage.m1.adapter.outbound.db.mapper.toEntity
import fr.uga.miage.m1.core.model.Hangar
import fr.uga.miage.m1.core.model.EtatHangar
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import java.util.*

class HangarRepositoryAdapterTest {

    private val jpaRepo: JpaHangarRepository = mockk()
    private val adapter = HangarRepositoryAdapter(jpaRepo)

    @Test
    fun `save should convert domain to entity and back`() {
        val hangar = Hangar(id = 1, capacite = 5, etat = EtatHangar.VIDE)
        every { jpaRepo.save(any<HangarEntity>()) } answers { firstArg() }

        val result = adapter.save(hangar)

        assertEquals(hangar.id, result.id)
        assertEquals(hangar.capacite, result.capacite)
        assertEquals(hangar.etat, result.etat)

        verify { jpaRepo.save(any<HangarEntity>()) }
    }

    @Test
    fun `findById returns domain object when present`() {
        val entity = HangarEntity(id = 1, capacite = 5, etat = EtatHangar.PLEIN)
        every { jpaRepo.findById(1) } returns Optional.of(entity)

        val result = adapter.findById(1)

        assertEquals(entity.id, result?.id)
        assertEquals(entity.capacite, result?.capacite)
        assertEquals(entity.etat, result?.etat)

        verify { jpaRepo.findById(1) }
    }

    @Test
    fun `findById returns null when absent`() {
        every { jpaRepo.findById(1) } returns Optional.empty()

        val result = adapter.findById(1)

        assertNull(result)
        verify { jpaRepo.findById(1) }
    }

    @Test
    fun `findAll returns list of domain objects`() {
        val entities = listOf(
            HangarEntity(id = 1, capacite = 5, etat = EtatHangar.VIDE),
            HangarEntity(id = 2, capacite = 3, etat = EtatHangar.PARTIELLEMENT_PLEIN)
        )
        every { jpaRepo.findAll() } returns entities

        val result = adapter.findAll()

        assertEquals(2, result.size)
        assertEquals(1, result[0].id)
        assertEquals(2, result[1].id)
        verify { jpaRepo.findAll() }
    }

    @Test
    fun `update should convert domain to entity and back`() {
        val hangar = Hangar(id = 1, capacite = 5, etat = EtatHangar.VIDE)
        every { jpaRepo.save(any<HangarEntity>()) } answers { firstArg() }

        val result = adapter.update(hangar)

        assertEquals(hangar.id, result.id)
        assertEquals(hangar.capacite, result.capacite)
        assertEquals(hangar.etat, result.etat)
        verify { jpaRepo.save(any<HangarEntity>()) }
    }

    @Test
    fun `delete should call deleteById on repository`() {
        every { jpaRepo.deleteById(1) } returns Unit

        adapter.delete(1)

        verify { jpaRepo.deleteById(1) }
    }
}
