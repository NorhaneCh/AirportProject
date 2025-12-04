package fr.uga.miage.m1.service

import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.model.Hangar
import fr.uga.miage.m1.core.port.HangarRepositoryPort
import fr.uga.miage.m1.core.service.HangarService
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertNull

class HangarServiceTest {

    private val repository: HangarRepositoryPort = mockk()
    private lateinit var service: HangarService

    @BeforeEach
    fun setup() {
        service = HangarService(repository)
        clearMocks(repository)
    }

    @Test
    fun `createHangar should save hangar`() {
        val hangar = Hangar(id = null, capacite = 2)
        every { repository.save(hangar) } returns hangar.copy(id = 1L)

        val result = service.createHangar(hangar)
        assertEquals(1L, result.id)
        verify { repository.save(hangar) }
    }

    @Test
    fun `getHangar should return hangar when exists`() {
        val hangar = Hangar(id = 1L, capacite = 2)
        every { repository.findById(1L) } returns hangar

        val result = service.getHangar(1L)
        assertEquals(hangar, result)
        verify { repository.findById(1L) }
    }

    @Test
    fun `getHangar should return null when not exists`() {
        every { repository.findById(1L) } returns null

        val result = service.getHangar(1L)
        assertNull(result)
        verify { repository.findById(1L) }
    }

    @Test
    fun `getAllHangars should return all hangars`() {
        val hangars = listOf(Hangar(id = 1L, capacite = 2))
        every { repository.findAll() } returns hangars

        val result = service.getAllHangars()
        assertEquals(hangars, result)
        verify { repository.findAll() }
    }

    @Test
    fun `updateHangar should update hangar`() {
        val hangar = Hangar(id = 1L, capacite = 2)
        every { repository.update(hangar) } returns hangar

        val result = service.updateHangar(hangar)
        assertEquals(hangar, result)
        verify { repository.update(hangar) }
    }

    @Test
    fun `deleteHangar should call repository delete`() {
        every { repository.delete(1L) } just Runs

        service.deleteHangar(1L)
        verify { repository.delete(1L) }
    }

    @Test
    fun `addAvionToHangar should add avion when hangar exists and has space`() {
        val avion = Avion("F-GAAA", "B737", 180)
        val hangar = Hangar(id = 1L, capacite = 2, avions = mutableListOf())
        every { repository.findById(1L) } returns hangar
        every { repository.update(any()) } answers { firstArg() }

        service.addAvionToHangar(1L, avion)
        assertTrue(hangar.avions.contains(avion))
        verify { repository.update(hangar) }
    }

    @Test
    fun `addAvionToHangar should do nothing when hangar does not exist`() {
        val avion = Avion("F-GAAA", "B737", 180)
        every { repository.findById(1L) } returns null

        service.addAvionToHangar(1L, avion)
        verify(exactly = 0) { repository.update(any()) }
    }

    @Test
    fun `addAvionToHangar should not add avion when hangar is full`() {
        val avion1 = Avion("F-GAAA", "B737", 180)
        val avion2 = Avion("F-GBBB", "A320", 150)
        val hangar = Hangar(id = 1L, capacite = 1, avions = mutableListOf(avion1))
        every { repository.findById(1L) } returns hangar
        every { repository.update(any()) } answers { firstArg() }

        service.addAvionToHangar(1L, avion2)
        assertFalse(hangar.avions.contains(avion2))
        verify { repository.update(hangar) }
    }

    @Test
    fun `removeAvionFromHangar should remove existing avion`() {
        val avion = Avion("F-GAAA", "B737", 180)
        val hangar = Hangar(id = 1L, capacite = 2, avions = mutableListOf(avion))
        every { repository.findById(1L) } returns hangar
        every { repository.update(any()) } answers { firstArg() }

        service.removeAvionFromHangar(1L, avion)
        assertFalse(hangar.avions.contains(avion))
        verify { repository.update(hangar) }
    }

    @Test
    fun `removeAvionFromHangar should do nothing if hangar does not exist`() {
        val avion = Avion("F-GAAA", "B737", 180)
        every { repository.findById(1L) } returns null

        service.removeAvionFromHangar(1L, avion)
        verify(exactly = 0) { repository.update(any()) }
    }

    @Test
    fun `removeAvionFromHangar should do nothing if avion not in hangar`() {
        val avion = Avion("F-GAAA", "B737", 180)
        val hangar = Hangar(id = 1L, capacite = 2, avions = mutableListOf())
        every { repository.findById(1L) } returns hangar
        every { repository.update(any()) } answers { firstArg() }

        service.removeAvionFromHangar(1L, avion)
        assertTrue(hangar.avions.isEmpty())
        verify { repository.update(hangar) }
    }
}
