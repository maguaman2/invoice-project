package com.example.invoicemg.controller

import com.example.invoicemg.model.Invoice
import com.example.invoicemg.service.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/invoice")
class InvoiceController {

    @Autowired
    lateinit var invoiceService: InvoiceService

    @GetMapping
    fun list ():ResponseEntity<*>{
        return ResponseEntity(invoiceService.list(), HttpStatus.OK)
    }

    @GetMapping("/totals/{total}")
    fun listTotals (@PathVariable("total") total: Double ):ResponseEntity<*>{
        return ResponseEntity(invoiceService.listTotalMoreThan(total), HttpStatus.OK)
    }

//    @GetMapping("/updatetotal/{invoiceId}")
//    fun updateFromDetails (@PathVariable("invoiceId") invoiceId: Long ):ResponseEntity<*>{
//        return ResponseEntity(invoiceService.updateFromDetails(invoiceId), HttpStatus.OK)
//    }


    @PostMapping
    fun save (@RequestBody invoice:Invoice):ResponseEntity<Invoice>{
        return ResponseEntity(invoiceService.save(invoice), HttpStatus.CREATED)
    }
    @PutMapping
    fun update (@RequestBody invoice:Invoice):ResponseEntity<Invoice>{
        return ResponseEntity(invoiceService.update(invoice), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody invoice:Invoice):ResponseEntity<Invoice>{
        return ResponseEntity(invoiceService.updateTotal(invoice), HttpStatus.OK)
    }
}