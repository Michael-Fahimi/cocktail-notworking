package com.example.countdown.ui.home

data class CocktailResponse(
    val drinks: List<Cocktail>
)

data class Cocktail(
    val strDrink: String,
    val strDrinkThumb: String,
    val idDrink: String
)