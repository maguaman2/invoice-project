package com.example.invoicemg.controller

import com.example.invoicemg.model.Details
import com.example.invoicemg.service.DetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/details")
class DetailsController {

    @Autowired
    lateinit var detailsService: DetailsService

    @GetMapping
    fun list ():ResponseEntity<*>{
        return ResponseEntity(detailsService.list(), HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody details:Details):ResponseEntity<Details>{
        return ResponseEntity(detailsService.save(details), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody details:Details):ResponseEntity<Details>{
        return ResponseEntity(detailsService.update(details), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody details:Details):ResponseEntity<Details>{
        return ResponseEntity(detailsService.updateTotal(details), HttpStatus.OK)
    }
}