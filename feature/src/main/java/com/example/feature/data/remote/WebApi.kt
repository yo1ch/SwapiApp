package com.example.feature.data.remote

import com.example.feature.data.remote.dto.FilmsResponse
import com.example.feature.data.remote.dto.PeopleResponse
import com.example.feature.data.remote.dto.PlanetsResponse
import com.example.feature.data.remote.dto.StarshipsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApi {

    @GET("people")
    suspend fun getCharacters(
        @Query("search") search: String
    ): Response<PeopleResponse>
    @GET("planets")
    suspend fun getPlanets(
        @Query("search") search: String
    ): Response<PlanetsResponse>
    @GET("starships")
    suspend fun getStarships(
        @Query("search") search: String
    ): Response<StarshipsResponse>
    @GET("films")
    suspend fun getFilms(): Response<FilmsResponse>

}