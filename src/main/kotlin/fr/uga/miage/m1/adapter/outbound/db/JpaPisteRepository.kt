package fr.uga.miage.m1.adapter.outbound.db

import fr.uga.miage.m1.adapter.outbound.db.entity.PisteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaPisteRepository : JpaRepository<PisteEntity, Long>
