package com.example.Dramtime.models

import com.example.Dramatime.models.MovieModel

data class GetMovieResponseModel(
    val page:Int,
    val total_pages:Int,
    val total_results:Int,
    val results:ArrayList<MovieModel>
)