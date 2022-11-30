package org.yellowhatpro.shoppincart.presentation.ui.features.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.yellowhatpro.shoppincart.data.repositories.FirebaseRepository
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository) : ViewModel() {
    private var _cartItems : MutableStateFlow<ArrayList<String>> = MutableStateFlow(arrayListOf())
    val cartItems = _cartItems.asStateFlow()
    private var _id : MutableStateFlow<String> = MutableStateFlow("")
    val id = _id.asStateFlow()
    fun getId(id:String){
            _id.value = (id)
            Log.d("getId",_id.value)
    }

    fun addToCart(productID: String, id: String){
       viewModelScope.launch {
           firebaseRepository.addProductToFirebase(productID, id)
       }
    }
}