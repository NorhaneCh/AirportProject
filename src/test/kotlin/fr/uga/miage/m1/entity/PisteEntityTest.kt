package fr.uga.miage.m1.entity
import fr.uga.miage.m1.adapter.outbound.db.entity.PisteEntity
import fr.uga.miage.m1.core.model.EtatPiste
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PisteEntityTest {

    @Test
    fun `test constructeur complet`() {
        val piste = PisteEntity(id = 1L, longueur = 3000, etat = EtatPiste.OCCUPEE)

        assertEquals(1L, piste.id)
        assertEquals(3000, piste.longueur)
        assertEquals(EtatPiste.OCCUPEE, piste.etat)
    }

    @Test
    fun `test constructeur par défaut`() {
        val piste = PisteEntity()

        assertNull(piste.id)
        assertEquals(0, piste.longueur)
        assertEquals(EtatPiste.LIBRE, piste.etat)
    }

    @Test
    fun `test setters`() {
        val piste = PisteEntity()

        piste.etat = EtatPiste.EN_MAINTENANCE
        assertEquals(EtatPiste.EN_MAINTENANCE, piste.etat)
    }
}
