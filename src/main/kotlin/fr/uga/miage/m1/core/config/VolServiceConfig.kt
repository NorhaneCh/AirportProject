package fr.uga.miage.m1.core.config

import fr.uga.miage.m1.core.service.VolService
import fr.uga.miage.m1.core.service.proxy.VolServiceProxy
import fr.uga.miage.m1.core.port.VolRepositoryPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VolServiceConfig(
    private val volRepository: VolRepositoryPort,
) {

    @Bean
    fun volServiceProxy(): VolServiceProxy {
        val realService = VolService(volRepository = volRepository)
        return VolServiceProxy(realService, userRole = "ADMIN")
    }
}
