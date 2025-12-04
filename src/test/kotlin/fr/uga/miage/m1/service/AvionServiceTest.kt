package fr.uga.miage.m1.service

import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.model.EtatAvion
import fr.uga.miage.m1.core.port.AvionRepositoryPort
import fr.uga.miage.m1.core.service.AvionService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AvionServiceTest {

    private val repository: AvionRepositoryPort = mockk()
    private val service = AvionService(repository)

    @Test
    fun `createAvion should save and return avion`() {
        val avion = Avion("F-GAAA", "B737", 180, EtatAvion.DISPONIBLE)
        every { repository.save(avion) } returns avion

        val result = service.createAvion(avion)

        assertEquals(avion, result)
        verify { repository.save(avion) }
    }

    @Test
    fun `getAvion should return avion when found`() {
        val immatriculation = "F-GAAA"
        val avion = Avion(immatriculation, "B737", 180, EtatAvion.EN_VOL)
        every { repository.findById(immatriculation) } returns avion

        val result = service.getAvion(immatriculation)

        assertEquals(avion, result)
        verify { repository.findById(immatriculation) }
    }

    @Test
    fun `getAvion should return null when not found`() {
        val immatriculation = "F-GAAA"
        every { repository.findById(immatriculation) } returns null

        val result = service.getAvion(immatriculation)

        assertNull(result)
        verify { repository.findById(immatriculation) }
    }

    @Test
    fun `getAllAvions should return list of avions`() {
        val avions = listOf(
            Avion("F-GAAA", "B737", 180, EtatAvion.DISPONIBLE),
            Avion("F-GBBB", "A320", 150, EtatAvion.EN_VOL)
        )
        every { repository.findAll() } returns avions

        val result = service.getAllAvions()

        assertEquals(avions, result)
        verify { repository.findAll() }
    }

    @Test
    fun `updateAvion should update and return avion`() {
        val avion = Avion("F-GAAA", "B737", 180, EtatAvion.DISPONIBLE)
        every { repository.update(avion) } returns avion

        val result = service.updateAvion(avion)

        assertEquals(avion, result)
        verify { repository.update(avion) }
    }

    @Test
    fun `deleteAvion should call repository delete`() {
        val immatriculation = "F-GAAA"
        every { repository.delete(immatriculation) } returns Unit

        service.deleteAvion(immatriculation)

        verify { repository.delete(immatriculation) }
    }
}
