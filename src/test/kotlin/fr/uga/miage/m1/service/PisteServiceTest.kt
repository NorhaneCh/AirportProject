package fr.uga.miage.m1.service

import fr.uga.miage.m1.core.model.Piste
import fr.uga.miage.m1.core.port.PisteRepositoryPort
import fr.uga.miage.m1.core.service.PisteService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class PisteServiceTest {

    private val repository: PisteRepositoryPort = mockk()
    private val service = PisteService(repository)

    @Test
    fun `createPiste should save and return piste`() {
        val piste = Piste(id = 1L, longueur = 3000)
        every { repository.save(piste) } returns piste

        val result = service.createPiste(piste)

        assertEquals(piste, result)
        verify { repository.save(piste) }
    }

    @Test
    fun `getPiste should return piste when found`() {
        val piste = Piste(id = 1L, longueur = 3000)
        every { repository.findById(1L) } returns piste

        val result = service.getPiste(1L)

        assertEquals(piste, result)
        verify { repository.findById(1L) }
    }

    @Test
    fun `getPiste should return null when not found`() {
        every { repository.findById(1L) } returns null

        val result = service.getPiste(1L)

        assertNull(result)
        verify { repository.findById(1L) }
    }

    @Test
    fun `getAllPistes should return list of pistes`() {
        val pistes = listOf(
            Piste(id = 1L, longueur = 3000),
            Piste(id = 2L, longueur = 2500)
        )
        every { repository.findAll() } returns pistes

        val result = service.getAllPistes()

        assertEquals(pistes, result)
        verify { repository.findAll() }
    }

    @Test
    fun `updatePiste should update and return piste`() {
        val piste = Piste(id = 1L, longueur = 3000)
        every { repository.update(piste) } returns piste

        val result = service.updatePiste(piste)

        assertEquals(piste, result)
        verify { repository.update(piste) }
    }

    @Test
    fun `deletePiste should call repository delete`() {
        every { repository.delete(1L) } returns Unit

        service.deletePiste(1L)

        verify { repository.delete(1L) }
    }
}
