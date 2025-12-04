package fr.uga.miage.m1.controller

import fr.uga.miage.m1.adapter.inbound.rest.HangarController
import fr.uga.miage.m1.core.model.Avion
import fr.uga.miage.m1.core.model.EtatAvion
import fr.uga.miage.m1.core.model.Hangar
import fr.uga.miage.m1.core.model.EtatHangar
import fr.uga.miage.m1.core.service.HangarService
import fr.uga.miage.m1.dto.AvionDto
import fr.uga.miage.m1.dto.AvionMapper
import fr.uga.miage.m1.dto.HangarDto
import fr.uga.miage.m1.dto.HangarMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals
import kotlin.test.assertNull

class HangarControllerTest {

    private val service: HangarService = mockk()
    private val controller = HangarController(service)

    @Test
    fun `create should return 201 with created hangar`() {
        val dto = HangarDto(id = null, capacite = 5, etat = EtatHangar.VIDE, avions = emptyList())
        val domain = HangarMapper.fromDto(dto, emptyList())

        every { service.createHangar(domain) } returns domain

        val response = controller.create(dto)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(HangarMapper.toDto(domain), response.body)
        verify { service.createHangar(domain) }
    }

    @Test
    fun `get should return 200 with hangar when found`() {
        val id = 1L
        val domain = Hangar(id = id, capacite = 5, etat = EtatHangar.VIDE)
        every { service.getHangar(id) } returns domain

        val response = controller.get(id)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(HangarMapper.toDto(domain), response.body)
        verify { service.getHangar(id) }
    }

    @Test
    fun `get should return 404 when hangar not found`() {
        val id = 1L
        every { service.getHangar(id) } returns null

        val response = controller.get(id)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertNull(response.body)
        verify { service.getHangar(id) }
    }

    @Test
    fun `getAll should return 200 with list of hangars`() {
        val hangars = listOf(
            Hangar(1L, 5, EtatHangar.VIDE),
            Hangar(2L, 10, EtatHangar.PLEIN)
        )
        every { service.getAllHangars() } returns hangars

        val response = controller.getAll()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(hangars.map { HangarMapper.toDto(it) }, response.body)
        verify { service.getAllHangars() }
    }

    @Test
    fun `update should return 200 with updated hangar`() {
        val id = 1L
        val dto = HangarDto(id = id, capacite = 7, etat = EtatHangar.PARTIELLEMENT_PLEIN)
        val domain = HangarMapper.fromDto(dto, emptyList())

        every { service.updateHangar(domain.copy(id = id)) } returns domain

        val response = controller.update(id, dto)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(HangarMapper.toDto(domain), response.body)
        verify { service.updateHangar(domain.copy(id = id)) }
    }

    @Test
    fun `delete should return 204`() {
        val id = 1L
        every { service.deleteHangar(id) } returns Unit

        val response = controller.delete(id)

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
        verify { service.deleteHangar(id) }
    }

    @Test
    fun `addAvion should return 201 with avion`() {
        val hangarId = 1L
        val dto = AvionDto("F-GAAA", "B737", 180, EtatAvion.DISPONIBLE)
        val domain = AvionMapper.fromDto(dto)

        every { service.addAvionToHangar(hangarId, domain) } returns Unit

        val response = controller.addAvion(hangarId, dto)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(dto, response.body)
        verify { service.addAvionToHangar(hangarId, domain) }
    }

    @Test
    fun `removeAvion should return 204`() {
        val hangarId = 1L
        val dto = AvionDto("F-GAAA", "B737", 180, EtatAvion.DISPONIBLE)
        val domain = AvionMapper.fromDto(dto)

        every { service.removeAvionFromHangar(hangarId, domain) } returns Unit

        val response = controller.removeAvion(hangarId, dto)

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
        verify { service.removeAvionFromHangar(hangarId, domain) }
    }

    @Test
    fun `listAvions should return 200 with list of avions`() {
        val hangarId = 1L
        val avions = listOf(
            Avion("F-GAAA", "B737", 180, EtatAvion.DISPONIBLE),
            Avion("F-GBBB", "A320", 150, EtatAvion.EN_VOL)
        )
        val hangar = Hangar(hangarId, 10, EtatHangar.VIDE, avions.toMutableList())
        every { service.getHangar(hangarId) } returns hangar

        val response = controller.listAvions(hangarId)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(avions.map { AvionMapper.toDto(it) }, response.body)
        verify { service.getHangar(hangarId) }
    }

    @Test
    fun `listAvions should return 404 when hangar not found`() {
        val hangarId = 1L
        every { service.getHangar(hangarId) } returns null

        val response = controller.listAvions(hangarId)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertNull(response.body)
        verify { service.getHangar(hangarId) }
    }
}
