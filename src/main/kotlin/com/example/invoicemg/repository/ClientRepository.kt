package com.example.invoicemg.repository

import com.example.invoicemg.model.Client
import com.example.invoicemg.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ClientRepository:JpaRepository<Client, Long> {
    fun findById(id: Long?):Client?


}