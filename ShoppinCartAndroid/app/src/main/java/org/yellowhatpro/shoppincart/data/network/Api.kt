package org.yellowhatpro.shoppincart.data.network

import org.yellowhatpro.shoppincart.data.entities.Product
import org.yellowhatpro.shoppincart.data.entities.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("products")
    suspend fun getProducts() : Response<Products>

    @GET("products/category/{category}")
    suspend fun getProductBasedOnCategory(
        @Path(value = "category", encoded = true)
        category: String
    ) : Response<Products>
}