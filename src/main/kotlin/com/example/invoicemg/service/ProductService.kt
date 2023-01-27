package com.example.invoicemg.service

import com.example.invoicemg.model.Product
import com.example.invoicemg.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.data.domain.Page

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    fun list(page:Long): Page<Product> {
        val paging: Pageable = PageRequest.of(page.toInt(), 2)
        return productRepository.findAll(paging)
    }

    fun save(product: Product): Product {
        try{
            product.description?.takeIf { validatePlate(it) }
                ?: throw Exception("Error placa")

            return productRepository.save(product)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update(product: Product): Product {
        try {
            productRepository.findById(product.id)
                ?: throw Exception("ID no existe")


            return productRepository.save(product)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


    fun updateTotal(product:Product): Product {
        try{
            val response = productRepository.findById(product.id)
                ?: throw Exception("ID no existe")
            response.apply {
                stock=product.stock
            }
            return productRepository.save(response)
            }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun validatePlate(plate:String):Boolean{
        return true
    }
}