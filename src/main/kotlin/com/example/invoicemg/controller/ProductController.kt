package com.example.invoicemg.controller

import com.example.invoicemg.model.Product
import com.example.invoicemg.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @GetMapping("{page},{size}")
    fun list (@RequestParam("page") page: Long):ResponseEntity<*>{
        return ResponseEntity(productService.list(page), HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody product:Product):ResponseEntity<Product>{
        return ResponseEntity(productService.save(product), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody product:Product):ResponseEntity<Product>{
        return ResponseEntity(productService.update(product), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody product:Product):ResponseEntity<Product>{
        return ResponseEntity(productService.updateTotal(product), HttpStatus.OK)
    }
}