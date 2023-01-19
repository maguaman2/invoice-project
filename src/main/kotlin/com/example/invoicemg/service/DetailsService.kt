package com.example.invoicemg.service

import com.example.invoicemg.model.Details
import com.example.invoicemg.model.Invoice
import com.example.invoicemg.model.Product
import com.example.invoicemg.repository.DetailsRepository
import com.example.invoicemg.repository.InvoiceRepository
import com.example.invoicemg.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DetailsService {
    @Autowired
    lateinit var detailsRepository: DetailsRepository

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    fun list(): List<Details> {
        return detailsRepository.findAll()
    }

    fun save(details: Details): Details {
        try{
            val response=detailsRepository.save(details)
            val responseProduct:Product? = productRepository.findById(response.productId)
            responseProduct?.apply {
                stock = stock?.minus(details.quantity!!)
            }
            productRepository.save(responseProduct!!)
            //llama a funcion para actualizar
            calculateAndUpdateTotal(response)
            return response
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update(details: Details): Details {
        try {
            detailsRepository.findById(details.id)
                ?: throw Exception("ID no existe")

            return detailsRepository.save(details)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


    fun updateTotal(details:Details): Details {
        try{
            val response = detailsRepository.findById(details.id)
                ?: throw Exception("ID no existe")
            response.apply {
                quantity=details.quantity
            }
            return detailsRepository.save(response)
            }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun calculateAndUpdateTotal (detail : Details){
        val totalCalculated = detailsRepository.sumTotal(detail.invoiceId)
        val invoiceResponse = invoiceRepository.findById(detail.invoiceId)
        invoiceResponse?.apply {
            total=totalCalculated
        }
        invoiceRepository.save(invoiceResponse!!)
    }
}