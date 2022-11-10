package com.example.invoicemg.controller

import com.example.invoicemg.model.Client
import com.example.invoicemg.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/client")
class ClientController {

    @Autowired
    lateinit var clientService: ClientService

    @GetMapping
    fun list ():ResponseEntity<*>{
        return ResponseEntity(clientService.list(), HttpStatus.OK)
    }
    @PostMapping
    fun save (@RequestBody client:Client):ResponseEntity<Client>{
        return ResponseEntity(clientService.save(client), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody client:Client):ResponseEntity<Client>{
        return ResponseEntity(clientService.update(client), HttpStatus.OK)
    }
}