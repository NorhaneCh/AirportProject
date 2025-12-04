package fr.uga.miage.m1.entity
import fr.uga.miage.m1.adapter.outbound.db.entity.AvionEntity
import fr.uga.miage.m1.adapter.outbound.db.entity.HangarEntity
import fr.uga.miage.m1.core.model.EtatHangar
import fr.uga.miage.m1.core.model.EtatAvion
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HangarEntityTest {

    @Test
    fun `test constructeur complet et valeurs par défaut`() {
        val hangar = HangarEntity(id = 1L, capacite = 5, etat = EtatHangar.PLEIN)

        assertEquals(1L, hangar.id)
        assertEquals(5, hangar.capacite)
        assertEquals(EtatHangar.PLEIN, hangar.etat)
        assertNotNull(hangar.avions)
        assertTrue(hangar.avions.isEmpty())
    }

    @Test
    fun `test constructeur par défaut`() {
        val hangar = HangarEntity()

        assertNull(hangar.id)
        assertEquals(0, hangar.capacite)
        assertEquals(EtatHangar.VIDE, hangar.etat)
        assertNotNull(hangar.avions)
        assertTrue(hangar.avions.isEmpty())
    }

    @Test
    fun `test ajouter et modifier la liste avions`() {
        val hangar = HangarEntity()
        val avion1 = AvionEntity("F-GAAA", "Boeing", 180, EtatAvion.DISPONIBLE)
        val avion2 = AvionEntity("F-GAAB", "Airbus", 150, EtatAvion.EN_VOL)

        hangar.avions.add(avion1)
        hangar.avions.add(avion2)

        assertEquals(2, hangar.avions.size)
        assertTrue(hangar.avions.contains(avion1))
        assertTrue(hangar.avions.contains(avion2))
    }
}
