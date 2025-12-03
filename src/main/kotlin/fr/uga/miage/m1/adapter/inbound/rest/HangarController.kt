package fr.uga.miage.m1.adapter.inbound.rest

import fr.uga.miage.m1.core.service.HangarService
import fr.uga.miage.m1.dto.HangarDto
import fr.uga.miage.m1.dto.HangarMapper
import fr.uga.miage.m1.dto.AvionDto
import fr.uga.miage.m1.dto.AvionMapper
import fr.uga.miage.m1.core.model.Avion
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hangars")
class HangarController(private val hangarService: HangarService) {

    @PostMapping
    fun create(@RequestBody dto: HangarDto): ResponseEntity<HangarDto> {
        val created = hangarService.createHangar(HangarMapper.fromDto(dto, emptyList()))
        return ResponseEntity.status(201).body(HangarMapper.toDto(created))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<HangarDto> {
        val hangar = hangarService.getHangar(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(HangarMapper.toDto(hangar))
    }

    @GetMapping
    fun getAll(): ResponseEntity<Collection<HangarDto>> {
        val hangars = hangarService.getAllHangars().map { HangarMapper.toDto(it) }
        return ResponseEntity.ok(hangars)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: HangarDto): ResponseEntity<HangarDto> {
        val updated = hangarService.updateHangar(
            HangarMapper.fromDto(dto, emptyList()).copy(id = id)
        )
        return ResponseEntity.ok(HangarMapper.toDto(updated))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        hangarService.deleteHangar(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{hangarId}/avions")
    fun addAvion(
        @PathVariable hangarId: Long,
        @RequestBody dto: AvionDto
    ): ResponseEntity<AvionDto> {
        val avion = AvionMapper.fromDto(dto)
        hangarService.addAvionToHangar(hangarId, avion)
        return ResponseEntity.status(201).body(AvionMapper.toDto(avion))
    }

    @DeleteMapping("/{hangarId}/avions")
    fun removeAvion(
        @PathVariable hangarId: Long,
        @RequestBody dto: AvionDto
    ): ResponseEntity<Void> {
        val avion = AvionMapper.fromDto(dto)
        hangarService.removeAvionFromHangar(hangarId, avion)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{hangarId}/avions")
    fun listAvions(@PathVariable hangarId: Long): ResponseEntity<Collection<AvionDto>> {
        val hangar = hangarService.getHangar(hangarId) ?: return ResponseEntity.notFound().build()
        val avionsDto = hangar.avions.map { AvionMapper.toDto(it) }
        return ResponseEntity.ok(avionsDto)
    }
}
