package com.example.Dramatime.retrofit

import com.example.Dramtime.models.GetMovieResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

val authToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjUxZjcyMTY1NmM2YTU0OGU2ZTRmNjA4ZGE0N2FkOSIsInN1YiI6IjY1MjUwMTlmMGNiMzM1MTZmZWM0ZTBhOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6XcDAbxqSGFSD-K-SjlmtxLbTQExl3NYliSdTm-1oAY"

interface ApiEndpoint {
    @GET("movie/popular?")
    fun getPopularMovie(
        @Query("language") language:String = "en-US",
        @Query("page") page:Int = 1,
        @Header("Authorization") token:String = authToken
    ): Call<GetMovieResponseModel>

    @GET("search/movie?")
    fun searchMovie(
        @Query("query") query:String,
        @Query("language") language:String = "en-US",
        @Query("page") page:Int = 1,
        @Header("Authorization") token:String = authToken
    ): Call<GetMovieResponseModel>
}