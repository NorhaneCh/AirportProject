package fr.uga.miage.m1.adapter.inbound.rest

import fr.uga.miage.m1.core.model.Piste
import fr.uga.miage.m1.core.service.PisteService
import fr.uga.miage.m1.dto.PisteDto
import fr.uga.miage.m1.dto.PisteMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pistes")
class PisteController(private val pisteService: PisteService) {

    @PostMapping
    fun create(@RequestBody dto: PisteDto): ResponseEntity<PisteDto> {
        val created = pisteService.createPiste(PisteMapper.fromDto(dto))
        return ResponseEntity.status(201).body(PisteMapper.toDto(created))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<PisteDto> {
        val piste = pisteService.getPiste(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(PisteMapper.toDto(piste))
    }

    @GetMapping
    fun getAll(): ResponseEntity<Collection<PisteDto>> {
        val pistes = pisteService.getAllPistes().map { PisteMapper.toDto(it) }
        return ResponseEntity.ok(pistes)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: PisteDto): ResponseEntity<PisteDto> {
        val updated = pisteService.updatePiste(
            PisteMapper.fromDto(dto).copy(id = id)
        )
        return ResponseEntity.ok(PisteMapper.toDto(updated))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        pisteService.deletePiste(id)
        return ResponseEntity.noContent().build()
    }
}
