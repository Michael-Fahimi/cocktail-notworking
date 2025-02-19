package com.example.countdown.ui.home


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiService {
    @GET("search.php")
    fun getCocktailsByFirstLetter(@Query("f") firstLetter: String): Call<CocktailResponse>
}