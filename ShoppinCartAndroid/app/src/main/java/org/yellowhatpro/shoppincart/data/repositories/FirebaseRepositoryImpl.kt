package org.yellowhatpro.shoppincart.data.repositories

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImpl : FirebaseRepository {

    private val db = Firebase.firestore


    override suspend fun setUserByID(id: String) {
        val data = mutableMapOf<String, ArrayList<String>>()
        val cart = "cart"
        data[cart] = arrayListOf("")
        db.collection("users").document(id)
            .set(
                data
            )
    }

    override suspend fun addProductToFirebase(productID: String, id: String) {
        db.collection("users").document(id).update(
            "cart", FieldValue.arrayUnion(productID)
        )
    }

    override suspend  fun getCartProducts(id: String): List<String> {
        var res = listOf<String>()
        db.collection("users").document(id).get()
            .addOnSuccessListener { document ->
                val data = document.data
                val cart = "cart"
                res = data!![cart] as List<String>
                Log.d("cartItms", data.toString())
            }
            .await()
        Log.d("cartIteems", res.toString())
        return res
    }

    override suspend fun deleteProductFromFirebase() {
        TODO("Not yet implemented")
    }
}