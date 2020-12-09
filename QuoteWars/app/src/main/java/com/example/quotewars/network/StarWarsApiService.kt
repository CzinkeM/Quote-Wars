package com.example.quotewars.network

import com.example.quotewars.model.QuoteModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://swquotesapi.digitaljedi.dk/api/SWQuote/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface StarWarsApiService {
    @GET("RandomStarWarsQuote")
    suspend fun getQuote(): QuoteModel
}
object SWApi{
    val retrofitService: StarWarsApiService by lazy {
        retrofit.create(StarWarsApiService::class.java)
    }
}