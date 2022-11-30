package org.yellowhatpro.shoppincart.data.repositories

import org.yellowhatpro.shoppincart.data.entities.Product
import org.yellowhatpro.shoppincart.data.entities.Products
import retrofit2.Response

interface MainRepository {
    suspend fun getProducts() : Response<Products>
    suspend fun getProductBasedOnCategory(category: String) : Response<Products>
}