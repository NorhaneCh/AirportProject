package fr.uga.miage.m1.adapter.inbound.rest

import fr.uga.miage.m1.core.model.StatutVol
import fr.uga.miage.m1.core.service.proxy.VolServiceProxy
import fr.uga.miage.m1.core.service.AvionService
import fr.uga.miage.m1.dto.VolDashboardDto
import fr.uga.miage.m1.dto.VolDto
import fr.uga.miage.m1.dto.mapper.VolMapper
import fr.uga.miage.m1.dto.mapper.VolDashboardMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vols")
class VolController(
    private val volService: VolServiceProxy,
    private val avionService: AvionService
) {

    @PostMapping
    fun creerVol(@RequestBody volDto: VolDto): ResponseEntity<VolDto> {
        val vol = VolMapper.fromDto(volDto)
        val saved = volService.creerVol(vol)
        return ResponseEntity(VolMapper.toDto(saved), HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun recupererVol(@PathVariable id: Long): ResponseEntity<VolDto> {
        val vol = volService.recupererVolParId(id)
            ?: return ResponseEntity.notFound().build()
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
        val avion = avionService.getAvion(avionImmatriculation)
            ?: throw IllegalArgumentException("Avion introuvable avec l'immatriculation $avionImmatriculation")
        val updated = volService.assignerAvion(id, avion)
        return ResponseEntity.ok(VolMapper.toDto(updated))
    }

    // ---- Partie Pistes ----

    @PatchMapping("/{id}/assignerPiste")
    fun assignerPiste(@PathVariable id: Long, @RequestParam pisteId: Long): ResponseEntity<VolDto> {
        val updated = volService.assignerPiste(id, pisteId)
        return ResponseEntity.ok(VolMapper.toDto(updated))
    }

    @PatchMapping("/{id}/libererPiste")
    fun libererPiste(@PathVariable id: Long): ResponseEntity<VolDto> {
        val updated = volService.libererPiste(id)
        return ResponseEntity.ok(VolMapper.toDto(updated))
    }

    @GetMapping("/{id}/piste")
    fun recupererPiste(@PathVariable id: Long): ResponseEntity<Long> {
        val vol = volService.recupererVolParId(id)
            ?: throw IllegalArgumentException("Vol introuvable")
        val pisteId = vol.pisteAssignee?.id
        return pisteId?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }

    // ---- Fin Partie Pistes ----

    @GetMapping
    fun listerTousLesVols(): ResponseEntity<Collection<VolDto>> {
        val vols = volService.listerTousLesVols().map { VolMapper.toDto(it) }
        return ResponseEntity.ok(vols)
    }

    @GetMapping("/statut/{statut}")
    fun listerVolsParStatut(@PathVariable statut: StatutVol): ResponseEntity<List<VolDto>> {
        val vols = volService.listerVolsParStatut(statut).map { VolMapper.toDto(it) }
        return ResponseEntity.ok(vols)
    }

    @GetMapping("/{id}/historique")
    fun historiqueStatuts(@PathVariable id: Long): ResponseEntity<List<StatutVol>> {
        val vol = volService.recupererVolParId(id)
            ?: throw IllegalArgumentException("Vol introuvable")
        return ResponseEntity.ok(vol.historiqueStatuts)
    }

    @GetMapping("/{id}/avion")
    fun recupererAvion(@PathVariable id: Long): ResponseEntity<String> {
        val vol = volService.recupererVolParId(id)
            ?: throw IllegalArgumentException("Vol introuvable")

        val avionImmat = vol.avionAssigne?.immatriculation
        return avionImmat?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }
    //Dashboard
    @GetMapping("/dashboard")
    fun dashboard(
        @RequestParam(required = false) statut: StatutVol?,
        @RequestParam(required = false) pisteId: Long?
    ): ResponseEntity<List<VolDashboardDto>> {

        // Récupérer tous les vols
        var vols = volService.listerTousLesVols()

        // Filtrage optionnel par statut
        statut?.let { vols = vols.filter { vol -> vol.statut == it } }

        // Filtrage optionnel par piste
        pisteId?.let { vols = vols.filter { vol -> vol.pisteAssignee?.id == it } }

        val dtoList = vols.map { VolDashboardMapper.toDto(it) }
        return ResponseEntity.ok(dtoList)
    }
//connection autre aéroport
    // ---- Connexion avec autre aéroport ----

    @GetMapping("/external/departures")
    fun getExternalDepartures(): ResponseEntity<List<VolDto>> {
        val vols = volService.listerTousLesVols()
            .filter { !it.isExternal && it.origine.lowercase() == "lino" } // uniquement mes vols
        val dtoList = vols.map { VolMapper.toDto(it) }
        return ResponseEntity.ok(dtoList)
    }

    @GetMapping("/external/arrivals")
    fun getExternalArrivals(): ResponseEntity<List<VolDto>> {
        val vols = volService.listerTousLesVols()
            .filter { !it.isExternal && it.destination.lowercase() == "lino" } // uniquement mes vols
        val dtoList = vols.map { VolMapper.toDto(it) }
        return ResponseEntity.ok(dtoList)
    }

    @PostMapping("/external/import")
    fun importerVolsExternes(@RequestBody volsExternes: List<VolDto>): ResponseEntity<Void> {
        volsExternes.forEach { dto ->
            val vol = VolMapper.fromDto(dto).copy(isExternal = true)
            if (volService.recupererVolParNumero(vol.numero) == null) {
                volService.creerVol(vol)
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
    @PostMapping("/external/fetch-arrivals")
    fun fetchExternalArrivals(): ResponseEntity<List<VolDto>> {
        val vols = volService.importerVolsExternesArrivals()
        return ResponseEntity.ok(vols.map { VolMapper.toDto(it) })
    }

    @PostMapping("/external/fetch-departures")
    fun fetchExternalDepartures(): ResponseEntity<List<VolDto>> {
        val vols = volService.importerVolsExternesDepartures()
        return ResponseEntity.ok(vols.map { VolMapper.toDto(it) })
    }

}
