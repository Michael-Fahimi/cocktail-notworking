package com.example.countdown

import com.example.countdown.ui.home.CocktailApiService
import com.example.countdown.ui.home.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    // create instances here and use it anywhere as injection
    @Provides
    fun provideConverter() : GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(
        converterFactory: GsonConverterFactory
    ) : Retrofit {

       return Retrofit.Builder()
            .baseUrl(RetrofitClient.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideApiClient(retrofit: Retrofit): CocktailApiService{
        return retrofit.create(CocktailApiService::class.java)
    }

}