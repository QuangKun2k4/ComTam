package fpl.quangnm.myapplication.api

data class Product(
    val id: Int? = null,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val description: String,
    val category: String
)
