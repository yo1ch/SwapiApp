package com.example.feature.data.remote

import com.example.feature.data.remote.dto.PeopleResponse
import com.example.feature.data.remote.dto.PlanetsResponse
import com.example.feature.data.remote.dto.StarshipsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApi {

    @GET("people")
    suspend fun getCharacters(): Response<PeopleResponse>
    @GET("planets")
    suspend fun getPlanets(): Response<PlanetsResponse>
    @GET("starships")
    suspend fun getStarships(): Response<StarshipsResponse>

}