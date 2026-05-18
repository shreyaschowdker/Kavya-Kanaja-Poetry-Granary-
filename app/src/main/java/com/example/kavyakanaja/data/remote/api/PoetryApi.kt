package com.example.kavyakanaja.data.remote.api

import com.example.kavyakanaja.data.remote.dto.PoemDto
import com.example.kavyakanaja.domain.model.Poet
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PoetryApi {
    @GET("poems")
    suspend fun getPoems(): List<PoemDto>

    @GET("poems/{id}")
    suspend fun getPoemById(@Path("id") id: String): PoemDto

    @GET("poems/today")
    suspend fun getPoemOfTheDay(): PoemDto

    @GET("poets")
    suspend fun getPoets(): List<Poet>

    @GET("poets/{id}")
    suspend fun getPoetById(@Path("id") id: String): Poet
}
