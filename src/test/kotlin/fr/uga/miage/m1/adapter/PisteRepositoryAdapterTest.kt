package fr.uga.miage.m1.adapter

import fr.uga.miage.m1.adapter.outbound.db.PisteRepositoryAdapter
import fr.uga.miage.m1.adapter.outbound.db.JpaPisteRepository
import fr.uga.miage.m1.adapter.outbound.db.entity.PisteEntity
import fr.uga.miage.m1.core.model.Piste
import fr.uga.miage.m1.core.model.EtatPiste
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import java.util.*

class PisteRepositoryAdapterTest {

    private val jpaRepo: JpaPisteRepository = mockk()
    private val adapter = PisteRepositoryAdapter(jpaRepo)

    @Test
    fun `save should convert domain to entity and back`() {
        val piste = Piste(id = 1, longueur = 2000, etat = EtatPiste.LIBRE)
        every { jpaRepo.save(any<PisteEntity>()) } answers { firstArg() }

        val result = adapter.save(piste)

        assertEquals(piste.id, result.id)
        assertEquals(piste.longueur, result.longueur)
        assertEquals(piste.etat, result.etat)

        verify { jpaRepo.save(any<PisteEntity>()) }
    }

    @Test
    fun `findById returns domain object when present`() {
        val entity = PisteEntity(id = 1, longueur = 2000, etat = EtatPiste.OCCUPEE)
        every { jpaRepo.findById(1) } returns Optional.of(entity)

        val result = adapter.findById(1)

        assertEquals(entity.id, result?.id)
        assertEquals(entity.longueur, result?.longueur)
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
            PisteEntity(id = 1, longueur = 2000, etat = EtatPiste.LIBRE),
            PisteEntity(id = 2, longueur = 1500, etat = EtatPiste.EN_MAINTENANCE)
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
        val piste = Piste(id = 1, longueur = 2000, etat = EtatPiste.LIBRE)
        every { jpaRepo.save(any<PisteEntity>()) } answers { firstArg() }

        val result = adapter.update(piste)

        assertEquals(piste.id, result.id)
        assertEquals(piste.longueur, result.longueur)
        assertEquals(piste.etat, result.etat)
        verify { jpaRepo.save(any<PisteEntity>()) }
    }

    @Test
    fun `delete should call deleteById on repository`() {
        every { jpaRepo.deleteById(1) } returns Unit

        adapter.delete(1)

        verify { jpaRepo.deleteById(1) }
    }
}
