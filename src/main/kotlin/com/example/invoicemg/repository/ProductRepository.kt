package com.example.invoicemg.repository

import com.example.invoicemg.model.Invoice
import com.example.invoicemg.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: PagingAndSortingRepository<Product, Long> {
  fun findById(id: Long?):Product?
}