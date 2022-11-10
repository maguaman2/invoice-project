package com.example.invoicemg.repository

import com.example.invoicemg.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository:JpaRepository<Client, Long> {
    fun findById(id: Long?):Client?
}