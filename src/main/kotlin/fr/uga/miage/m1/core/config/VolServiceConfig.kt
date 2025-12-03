package fr.uga.miage.m1.core.config

import fr.uga.miage.m1.core.service.VolService
import fr.uga.miage.m1.core.service.proxy.VolServiceProxy
import fr.uga.miage.m1.core.port.VolRepositoryPort
import fr.uga.miage.m1.core.port.AvionRepositoryPort
import fr.uga.miage.m1.core.port.PisteRepositoryPort
import fr.uga.miage.m1.core.service.PisteService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
@Configuration
class VolServiceConfig(
    private val volRepository: VolRepositoryPort,
    private val avionRepository: AvionRepositoryPort,
    private val pisteRepository: PisteRepositoryPort,
    private val pisteService: PisteService
) {

    @Bean
    fun volServiceProxy(): VolServiceProxy {
        val realService = VolService(
            volRepository = volRepository,
            avionRepository = avionRepository,
            pisteRepository = pisteRepository,
            pisteService = pisteService
        )
        return VolServiceProxy(realService, userRole = "ADMIN")
    }
}
