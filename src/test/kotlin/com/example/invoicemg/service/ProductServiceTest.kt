package com.example.invoicemg.service

import com.example.invoicemg.model.Client
import com.example.invoicemg.model.Product
import com.example.invoicemg.repository.ClientRepository
import com.example.invoicemg.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductServiceTest {

    @InjectMocks
    lateinit var productService: ProductService

    @Mock
    lateinit var productRepository: ProductRepository

    val productMock = Product().apply {
        id=1
        description="Iphone"
        brand="Apple"
        stock= 0
        price=2.3
    }

    @Test
    fun saveProductWhenIsCorrect(){
        Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
        val response = productService.save(productMock)
        Assertions.assertEquals(response.id, productMock.id)
    }



}