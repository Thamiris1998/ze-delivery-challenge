package com.ze.partner.application.controller

import com.ze.partner.application.domain.Partner
import com.ze.partner.application.model.PartnerModel
import com.ze.partner.application.service.PartnerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import java.net.URI

@RestController
@RequestMapping("partner")
class PartnerController (private val partnerService: PartnerService){

    private val logger: Logger = LoggerFactory.getLogger(PartnerController::class.java)

    @GetMapping("/nearest/long/{long}/lat/{lat}")
    fun findCloserCoverageArea(@PathVariable("long") long : Double, @PathVariable("lat") lat : Double): ResponseEntity<Partner> {
        logger.info("Start find by nearest between long:$long lat:$lat")
        val partner = partnerService.findNearest(long,lat)
        return ResponseEntity.ok(partner)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): ResponseEntity<Partner> {
        logger.info("Start FindById:$id")
        val partner = partnerService.findById(id)
        return ResponseEntity.ok(partner)
    }

    @PostMapping
    fun save(@RequestBody @Valid model: PartnerModel): ResponseEntity<Partner> {
        logger.info("Start save:$model")
        val partner = partnerService.save(model)
        return  ResponseEntity.created(URI.create("/partner")).body(partner)
    }
}