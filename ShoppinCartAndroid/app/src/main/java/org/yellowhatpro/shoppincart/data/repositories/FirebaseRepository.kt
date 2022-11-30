package org.yellowhatpro.shoppincart.data.repositories


interface FirebaseRepository {

    suspend fun setUserByID(id:String)
    suspend fun addProductToFirebase(productID: String, id: String)
    suspend fun getCartProducts(id:String) : List<String>
    suspend fun deleteProductFromFirebase()
}