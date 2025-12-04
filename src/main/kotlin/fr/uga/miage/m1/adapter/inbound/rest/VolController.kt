package fr.uga.miage.m1.adapter.inbound.rest

import fr.uga.miage.m1.core.model.StatutVol
import fr.uga.miage.m1.core.service.proxy.VolServiceProxy
import fr.uga.miage.m1.core.service.AvionService
import fr.uga.miage.m1.core.service.VolService
import fr.uga.miage.m1.dto.VolDashboardDto
import fr.uga.miage.m1.dto.VolDto
import fr.uga.miage.m1.dto.mapper.VolMapper
import fr.uga.miage.m1.dto.mapper.VolDashboardMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vols")
class VolController(private val volService: VolService) {

    @PostMapping
    fun creerVol(@RequestBody volDto: VolDto): ResponseEntity<VolDto> {
        val vol = VolMapper.fromDto(volDto)
        val saved = volService.creerVol(vol)
        return ResponseEntity.status(HttpStatus.CREATED).body(VolMapper.toDto(saved))
    }

    @GetMapping("/{id}")
    fun recupererVol(@PathVariable id: Long): ResponseEntity<VolDto> {
        val vol = volService.recupererVolParId(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(VolMapper.toDto(vol))
    }

    @PutMapping("/{id}")
    fun modifierVol(@PathVariable id: Long, @RequestBody volDto: VolDto): ResponseEntity<VolDto> {
        val vol = VolMapper.fromDto(volDto).copy(id = id)
        val updated = volService.modifierVol(vol)
        return ResponseEntity.ok(VolMapper.toDto(updated))
    }

    @DeleteMapping("/{id}")
    fun supprimerVol(@PathVariable id: Long): ResponseEntity<Void> {
        volService.supprimerVol(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}/statut")
    fun changerStatut(@PathVariable id: Long, @RequestParam statut: StatutVol): ResponseEntity<VolDto> {
        val updated = volService.changerStatutVol(id, statut)
        return ResponseEntity.ok(VolMapper.toDto(updated))
    }

    @PatchMapping("/{id}/assignerAvion")
    fun assignerAvion(@PathVariable id: Long, @RequestParam avionImmatriculation: String): ResponseEntity<VolDto> {
        val updated = volService.assignerAvion(id, avionImmatriculation)
        return ResponseEntity.ok(VolMapper.toDto(updated))
    }

    @PatchMapping("/{id}/assignerPiste")
    fun assignerPiste(@PathVariable id: Long, @RequestParam pisteId: Long): ResponseEntity<VolDto> {
        val updated = volService.assignerPiste(id, pisteId)
        return ResponseEntity.ok(VolMapper.toDto(updated))
    }

    @GetMapping
    fun listerTousLesVols(): ResponseEntity<List<VolDto>> {
        val vols = volService.listerTousLesVols().map { VolMapper.toDto(it) }
        return ResponseEntity.ok(vols)
    }
}
