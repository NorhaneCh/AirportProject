package fr.uga.miage.m1.controller

import fr.uga.miage.m1.adapter.inbound.rest.PisteController
import fr.uga.miage.m1.core.model.Piste
import fr.uga.miage.m1.core.model.EtatPiste
import fr.uga.miage.m1.core.service.PisteService
import fr.uga.miage.m1.dto.PisteDto
import fr.uga.miage.m1.dto.PisteMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals
import kotlin.test.assertNull

class PisteControllerTest {

    private val service: PisteService = mockk()
    private val controller = PisteController(service)

    @Test
    fun `create should return 201 with created piste`() {
        val dto = PisteDto(id = null, longueur = 1000, etat = EtatPiste.LIBRE)
        val domain = PisteMapper.fromDto(dto)

        every { service.createPiste(domain) } returns domain

        val response = controller.create(dto)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(PisteMapper.toDto(domain), response.body)
        verify { service.createPiste(domain) }
    }

    @Test
    fun `get should return 200 with piste when found`() {
        val id = 1L
        val domain = Piste(id = id, longueur = 1200, etat = EtatPiste.OCCUPEE)
        every { service.getPiste(id) } returns domain

        val response = controller.get(id)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(PisteMapper.toDto(domain), response.body)
        verify { service.getPiste(id) }
    }

    @Test
    fun `get should return 404 when piste not found`() {
        val id = 1L
        every { service.getPiste(id) } returns null

        val response = controller.get(id)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertNull(response.body)
        verify { service.getPiste(id) }
    }

    @Test
    fun `getAll should return 200 with list of pistes`() {
        val pistes = listOf(
            Piste(1L, 1000, EtatPiste.LIBRE),
            Piste(2L, 1500, EtatPiste.EN_MAINTENANCE)
        )
        every { service.getAllPistes() } returns pistes

        val response = controller.getAll()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(pistes.map { PisteMapper.toDto(it) }, response.body)
        verify { service.getAllPistes() }
    }

    @Test
    fun `update should return 200 with updated piste`() {
        val id = 1L
        val dto = PisteDto(id = id, longueur = 1100, etat = EtatPiste.LIBRE)
        val domain = PisteMapper.fromDto(dto)

        every { service.updatePiste(domain.copy(id = id)) } returns domain

        val response = controller.update(id, dto)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(PisteMapper.toDto(domain), response.body)
        verify { service.updatePiste(domain.copy(id = id)) }
    }

    @Test
    fun `delete should return 204`() {
        val id = 1L
        every { service.deletePiste(id) } returns Unit

        val response = controller.delete(id)

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
        verify { service.deletePiste(id) }
    }
}
