package fr.uga.miage.m1.entity
import fr.uga.miage.m1.adapter.outbound.db.entity.AvionEntity
import fr.uga.miage.m1.core.model.EtatAvion
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AvionEntityTest {

    @Test
    fun `test constructeur complet et getters`() {
        val now = LocalDateTime.now()
        val avion = AvionEntity(
            immatriculation = "F-GAAA",
            type = "Boeing 737",
            capacite = 180,
            etat = EtatAvion.DISPONIBLE,
            createdAt = now
        )

        assertEquals("F-GAAA", avion.immatriculation)
        assertEquals("Boeing 737", avion.type)
        assertEquals(180, avion.capacite)
        assertEquals(EtatAvion.DISPONIBLE, avion.etat)
        assertEquals(now, avion.createdAt)
    }

    @Test
    fun `test constructeur par défaut`() {
        val avion = AvionEntity()

        assertEquals("", avion.immatriculation)
        assertEquals("", avion.type)
        assertEquals(0, avion.capacite)
        assertEquals(EtatAvion.DISPONIBLE, avion.etat)
        assertNotNull(avion.createdAt)
    }

    @Test
    fun `test setters`() {
        val avion = AvionEntity()
        val now = LocalDateTime.now()

        avion.immatriculation = "F-GAAB"
        avion.type = "Airbus A320"
        avion.capacite = 150
        avion.etat = EtatAvion.EN_VOL
        avion.createdAt = now

        assertEquals("F-GAAB", avion.immatriculation)
        assertEquals("Airbus A320", avion.type)
        assertEquals(150, avion.capacite)
        assertEquals(EtatAvion.EN_VOL, avion.etat)
        assertEquals(now, avion.createdAt)
    }
}
