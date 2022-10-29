package org.yellowhatpro.shoppincart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.yellowhatpro.shoppincart.data.entities.Category.*
import org.yellowhatpro.shoppincart.data.entities.Product
import org.yellowhatpro.shoppincart.data.entities.categories
import org.yellowhatpro.shoppincart.data.repositories.MainRepository
import javax.inject.Inject

@HiltViewModel
class ShoppinCartViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    init {
        for (category in categories){
            getProductBasedOnCategory(category.endpoint)
        }
    }

    private val _allProducts: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _smartphones: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _laptops: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _fragrances: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _skincare: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _groceries: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _homeDecoration: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _furniture: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _tops: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _womensDresses: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _womenShoes: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _mensShirts: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _mensShoes: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _mensWatches: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _womensWatches: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _womensBags: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _womensJewellery: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _sunglasses: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _automotives: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _motorcycle: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    private val _lighting: MutableStateFlow<List<Product?>> = MutableStateFlow(listOf(Product()))
    val allProducts = _allProducts.asStateFlow()
    val smartphones = _smartphones.asStateFlow()
    val laptop = _laptops.asStateFlow()
    val fragrances = _fragrances.asStateFlow()
    val skincare = _skincare.asStateFlow()
    val groceries = _groceries.asStateFlow()
    val homeDecoration = _homeDecoration.asStateFlow()
    val furniture = _furniture.asStateFlow()
    val tops = _tops.asStateFlow()
    val womensDresses = _womensDresses.asStateFlow()
    val womenShoes = _womenShoes.asStateFlow()
    val mensShirts = _mensShirts.asStateFlow()
    val mensShoes = _mensShoes.asStateFlow()
    val mensWatches = _mensWatches.asStateFlow()
    val womensWatches = _womensWatches.asStateFlow()
    val womensBags = _womensBags.asStateFlow()
    val womensJewellery = _womensJewellery.asStateFlow()
    val sunglasses = _sunglasses.asStateFlow()
    val automotives = _automotives.asStateFlow()
    val motorcycle = _motorcycle.asStateFlow()
    val lighting = _lighting.asStateFlow()
    private fun getAllProducts() {
        viewModelScope.launch {
            val response = mainRepository.getProducts()
            if (response.isSuccessful) {
                _allProducts.value = response.body()?.products ?: listOf(Product())
            }
        }
    }

    private fun getProductBasedOnCategory(category: String) {
        viewModelScope.launch {
            val response = mainRepository.getProductBasedOnCategory(category)
            if (response.isSuccessful) {
                when (category) {
                    Smartphones.endpoint -> _smartphones.value =
                        response.body()?.products ?: listOf(Product())

                    Laptops.endpoint -> _laptops.value =
                        response.body()?.products ?: listOf(Product())

                    Fragrances.endpoint -> _fragrances.value =
                        response.body()?.products ?: listOf(Product())

                    Skincare.endpoint -> _skincare.value =
                        response.body()?.products ?: listOf(Product())

                    Groceries.endpoint -> _groceries.value =
                        response.body()?.products ?: listOf(Product())

                    HomeDecoration.endpoint -> _homeDecoration.value =
                        response.body()?.products ?: listOf(Product())

                    Furniture.endpoint -> _furniture.value =
                        response.body()?.products ?: listOf(Product())

                    Tops.endpoint -> _tops.value = response.body()?.products ?: listOf(Product())
                    WomensDresses.endpoint -> _womensDresses.value =
                        response.body()?.products ?: listOf(Product())

                    WomensShoes.endpoint -> _womenShoes.value =
                        response.body()?.products ?: listOf(Product())

                    MensShirts.endpoint -> _mensShirts.value =
                        response.body()?.products ?: listOf(Product())

                    MensShoes.endpoint -> _mensShoes.value =
                        response.body()?.products ?: listOf(Product())

                    MensWatches.endpoint -> _mensWatches.value =
                        response.body()?.products ?: listOf(Product())

                    WomensWatches.endpoint -> _womensWatches.value =
                        response.body()?.products ?: listOf(Product())

                    WomensBags.endpoint -> _womensBags.value =
                        response.body()?.products ?: listOf(Product())

                    WomensJewellery.endpoint -> _womensJewellery.value =
                        response.body()?.products ?: listOf(Product())

                    Sunglasses.endpoint -> _sunglasses.value =
                        response.body()?.products ?: listOf(Product())

                    Automotive.endpoint -> _automotives.value =
                        response.body()?.products ?: listOf(Product())

                    Motorcycle.endpoint -> _motorcycle.value =
                        response.body()?.products ?: listOf(Product())

                    Lighting.endpoint -> _lighting.value =
                        response.body()?.products ?: listOf(Product())
                }
            }
        }
    }
}