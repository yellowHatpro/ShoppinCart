package org.yellowhatpro.shoppincart.data.entities

import org.yellowhatpro.shoppincart.data.entities.Category.*

sealed class Category(var name: String,var endpoint: String){
    object Smartphones : Category("Smartphones", "smartphones")
    object Laptops : Category("Laptops", "laptops")
    object Fragrances : Category("Fragrances", "fragrances")
    object Skincare : Category("Skincare", "skincare")
    object Groceries : Category("Groceries", "groceries")
    object HomeDecoration : Category("Home Decoration", "home-decoration")
    object Furniture : Category("Furniture", "furniture")
    object Tops : Category("Tops", "tops")
    object WomensDresses : Category("Women's Dresses", "womens-dresses")
    object WomensShoes : Category("Women's Shoes", "womens-shoes")
    object MensShirts : Category("Men's Shirts", "mens-shirts")
    object MensShoes : Category("Men's Shoes", "mens-shoes")
    object MensWatches : Category("Men's Watches", "mens-watches")
    object WomensWatches : Category("Women's Watches", "womens-watches")
    object WomensBags: Category("Women's Bags", "womens-bags")
    object WomensJewellery: Category("Women's Jewellery", "womens-jewellery")
    object Sunglasses: Category("Sunglasses", "sunglasses")
    object Automotive: Category("Automotive", "automotive")
    object Motorcycle: Category("Motorcycle", "motorcycle")
    object Lighting: Category("Lighting", "lighting")
}
val categories = listOf(
    Smartphones,
    Laptops,
    Fragrances,
    Skincare,
    Groceries,
    HomeDecoration,
    Furniture,
    Tops,
    WomensDresses,
    WomensShoes,
    MensShirts,
    MensShoes,
    MensWatches,
    WomensWatches,
    WomensBags,
    WomensJewellery,
    Sunglasses,
    Automotive,
    Motorcycle,
    Lighting
)

