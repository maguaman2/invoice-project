package com.example.invoicemg.service

import com.example.invoicemg.model.Invoice
import com.example.invoicemg.model.InvoiceView
import com.example.invoicemg.repository.ClientRepository
import com.example.invoicemg.repository.InvoiceRepository
import com.example.invoicemg.repository.InvoiceViewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class InvoiceService {
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Autowired
    lateinit var invoiceViewRepository: InvoiceViewRepository

    @Autowired
    lateinit var clientRepository: ClientRepository

    fun list(): List<Invoice> {
        return invoiceRepository.findAll()
    }

    fun listWithClient(): List<InvoiceView> {
        return invoiceViewRepository.findAll()
    }
    fun listTotalMoreThan(total:Double?): List<Invoice>? {
        return invoiceRepository.findTotalMoreThan(total)
    }

    fun save(invoice: Invoice): Invoice {
        try{
        ///validar qe el cliente xiste
            clientRepository.findById(invoice.clientId)
                ?: throw Exception("El id  de cliente no existe")

            return invoiceRepository.save(invoice)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }


    }

    fun update(invoice: Invoice): Invoice {
        try {
            invoiceRepository.findById(invoice.id)
                ?: throw Exception("ID no existe")

            return invoiceRepository.save(invoice)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


    fun updateTotal(invoice:Invoice): Invoice {
        try{
            val response = invoiceRepository.findById(invoice.id)
                ?: throw Exception("ID no existe")

            response.apply {
                total=invoice.total
            }
            return invoiceRepository.save(response)
            }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}