package fr.uga.miage.m1.entityMapper
import fr.uga.miage.m1.adapter.outbound.db.entity.PisteEntity
import fr.uga.miage.m1.adapter.outbound.db.mapper.toDomain
import fr.uga.miage.m1.adapter.outbound.db.mapper.toEntity
import fr.uga.miage.m1.core.model.Piste
import fr.uga.miage.m1.core.model.EtatPiste
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PisteEntityMapperTest {

    @Test
    fun `test mapping domain to entity`() {
        val piste = Piste(id = 1L, longueur = 3000, etat = EtatPiste.OCCUPEE)
        val entity = piste.toEntity()

        assertEquals(piste.id, entity.id)
        assertEquals(piste.longueur, entity.longueur)
        assertEquals(piste.etat, entity.etat)
    }

    @Test
    fun `test mapping entity to domain`() {
        val entity = PisteEntity(id = 1L, longueur = 3000, etat = EtatPiste.EN_MAINTENANCE)
        val piste = entity.toDomain()

        assertEquals(entity.id ?: 0L, piste.id)
        assertEquals(entity.longueur, piste.longueur)
        assertEquals(entity.etat, piste.etat)
    }
}
