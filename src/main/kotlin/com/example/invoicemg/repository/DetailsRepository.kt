package com.example.invoicemg.repository

import com.example.invoicemg.model.Details
import com.example.invoicemg.model.Invoice
import com.example.invoicemg.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DetailsRepository:JpaRepository<Details, Long> {
  fun findById(id: Long?):Details?

  @Query(nativeQuery = true)   //Va a leer jpa-named.....
  fun sumTotal(@Param ("invoiceId") invoiceId: Long?): Double?
}