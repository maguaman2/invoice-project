package com.example.invoicemg.repository

import com.example.invoicemg.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository:JpaRepository<Invoice, Long> {

    fun findById(id: Long?):Invoice?

    @Query(nativeQuery = true)  //Va a leer jpa-named.....
    fun findTotalMoreThan(@Param ("total") total: Double?):List<Invoice>?

//    @Modifying  //Va a leer jpa-named.....
//    fun updateFromDetails(@Param ("invoiceId") invoiceId: Long?): Unit?

}