package fr.uga.miage.m1.controller

import fr.uga.miage.m1.adapter.inbound.rest.AvionController
import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.model.EtatAvion
import fr.uga.miage.m1.core.service.AvionService
import fr.uga.miage.m1.dto.AvionDto
import fr.uga.miage.m1.dto.AvionMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AvionControllerTest {

    private val service: AvionService = mockk()
    private val controller = AvionController(service)

    @Test
    fun `create should return 201 with created avion`() {
        val dto = AvionDto("F-GAAA", "B737", 180, EtatAvion.DISPONIBLE)
        val domain = AvionMapper.fromDto(dto)

        every { service.createAvion(domain) } returns domain

        val response = controller.create(dto)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(dto, response.body)

        verify { service.createAvion(domain) }
    }

    @Test
    fun `get should return 200 with avion when found`() {
        val immat = "F-GAAA"
        val domain = Avion(immat, "B737", 180, EtatAvion.DISPONIBLE)
        every { service.getAvion(immat) } returns domain

        val response = controller.get(immat)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(AvionMapper.toDto(domain), response.body)

        verify { service.getAvion(immat) }
    }

    @Test
    fun `get should return 404 when avion not found`() {
        val immat = "F-GAAA"
        every { service.getAvion(immat) } returns null

        val response = controller.get(immat)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertNull(response.body)

        verify { service.getAvion(immat) }
    }

    @Test
    fun `getAll should return 200 with list of avions`() {
        val avions = listOf(
            Avion("F-GAAA", "B737", 180, EtatAvion.DISPONIBLE),
            Avion("F-GBBB", "A320", 150, EtatAvion.EN_VOL)
        )
        every { service.getAllAvions() } returns avions

        val response = controller.getAll()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(avions.map { AvionMapper.toDto(it) }, response.body)

        verify { service.getAllAvions() }
    }

    @Test
    fun `update should return 200 with updated avion`() {
        val immat = "F-GAAA"
        val dto = AvionDto(immat, "B737", 180, EtatAvion.EN_VOL)
        val domain = AvionMapper.fromDto(dto)
        every { service.updateAvion(domain.copy(immatriculation = immat)) } returns domain

        val response = controller.update(immat, dto)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(dto, response.body)

        verify { service.updateAvion(domain.copy(immatriculation = immat)) }
    }

    @Test
    fun `delete should return 204`() {
        val immat = "F-GAAA"
        every { service.deleteAvion(immat) } returns Unit

        val response = controller.delete(immat)

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)

        verify { service.deleteAvion(immat) }
    }
}
