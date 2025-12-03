    package fr.uga.miage.m1.core.port

    import fr.uga.miage.m1.core.model.Vol
    import fr.uga.miage.m1.core.model.StatutVol
    import java.util.Optional

    interface VolRepositoryPort {

        fun save(vol: Vol): Vol
        fun findById(id: Long): Vol?
        fun findByNumero(numero: String): Vol?
        fun deleteById(id: Long)
        fun findAllByStatut(statut: StatutVol): Collection<Vol>
        fun findAll(): Collection<Vol>
    }
