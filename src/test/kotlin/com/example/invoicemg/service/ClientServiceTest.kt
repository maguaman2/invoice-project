package com.example.invoicemg.service

import com.example.invoicemg.model.Client
import com.example.invoicemg.repository.ClientRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ClientServiceTest {

    @InjectMocks
    lateinit var clientService: ClientService

    @Mock
    lateinit var clientRepository: ClientRepository

    val clientMock = Client().apply {
        id=1
        nui="0301707030"
        fullname="Juan"
        address= "Ceunca"
    }

    @Test
    fun saveClienteWhenIsCorrect(){
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.save(clientMock)
        Assertions.assertEquals(response.id, clientMock.id)
    }

    @Test
    fun saveClienteWhenIsFullnameIsBlank(){
        Assertions.assertThrows(Exception::class.java) {
            clientMock.apply { fullname="         "}
            Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
            clientService.save(clientMock)
        }
    }

}