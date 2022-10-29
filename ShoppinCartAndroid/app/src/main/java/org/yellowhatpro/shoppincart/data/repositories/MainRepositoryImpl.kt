package org.yellowhatpro.shoppincart.data.repositories

import org.yellowhatpro.shoppincart.data.entities.Products
import org.yellowhatpro.shoppincart.data.network.Api
import retrofit2.Response

class MainRepositoryImpl(private val api : Api) : MainRepository {
    override suspend fun getProducts(): Response<Products> =
        api.getProducts()

    override suspend fun getProductBasedOnCategory(category: String): Response<Products> =
        api.getProductBasedOnCategory(category)
}