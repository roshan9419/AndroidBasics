package com.lpu.cse225ca1.models

// DATA MODEL OF ITEMS INSIDE SCROLL VIEW
data class CardItem(
    val id: Int,
    val title: String,
    val imageUrl: String,
    var rating: Float = -1.0f
)