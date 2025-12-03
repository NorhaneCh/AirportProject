package fr.uga.miage.m1.adapter.inbound.rest

import fr.uga.miage.m1.core.service.AvionService
import fr.uga.miage.m1.dto.AvionDto
import fr.uga.miage.m1.dto.AvionMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@CrossOrigin(origins = ["http://localhost:5173"])
@RestController
@RequestMapping("/avions")
class AvionController(private val avionService: AvionService) {

    @PostMapping
    fun create(@RequestBody dto: AvionDto): ResponseEntity<AvionDto> {
        val created = avionService.createAvion(
            AvionMapper.fromDto(dto)
        )
        return ResponseEntity.status(201).body(AvionMapper.toDto(created))
    }

    @GetMapping("/{immatriculation}")
    fun get(@PathVariable immatriculation: String): ResponseEntity<AvionDto> {
        val avion = avionService.getAvion(immatriculation)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(AvionMapper.toDto(avion))
    }

    @GetMapping
    fun getAll(): ResponseEntity<Collection<AvionDto>> {
        val avions = avionService.getAllAvions()
            .map { AvionMapper.toDto(it) }

        return ResponseEntity.ok(avions)
    }

    @PutMapping("/{immatriculation}")
    fun update(
        @PathVariable immatriculation: String,
        @RequestBody dto: AvionDto
    ): ResponseEntity<AvionDto> {

        val updated = avionService.updateAvion(
            AvionMapper.fromDto(dto).copy(
                immatriculation = immatriculation
            )
        )

        return ResponseEntity.ok(AvionMapper.toDto(updated))
    }

    @DeleteMapping("/{immatriculation}")
    fun delete(@PathVariable immatriculation: String): ResponseEntity<Void> {
        avionService.deleteAvion(immatriculation)
        return ResponseEntity.noContent().build()
    }
}
