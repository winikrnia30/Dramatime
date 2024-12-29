package com.example.Dramatime.models

data class MovieModel(
    val adult:Boolean,
    val id:Int,
    val title:String,
    val overview:String,
    val popularity:Float,
    val poster_path:String,
    val release_date:String,
    val vote_average:Float,
    val vote_count:Int
)
