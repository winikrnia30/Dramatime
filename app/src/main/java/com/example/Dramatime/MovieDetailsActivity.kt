package com.example.Dramatime

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.Dramatime.models.MovieModel
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var movieThumbnail: ImageView
    private lateinit var movieTitle:TextView
    private lateinit var movieReleaseDate:TextView
    private lateinit var movieRating:TextView
    private lateinit var moviePopularity:TextView
    private lateinit var movieVoteCount:TextView
    private lateinit var movieOverview:TextView
    private lateinit var contactUsTextView:TextView

    fun initComponents(){
        movieThumbnail = findViewById(R.id.movieThumbnail)
        movieTitle = findViewById(R.id.movieTitle)
        movieReleaseDate = findViewById(R.id.movieReleaseDate)
        movieRating = findViewById(R.id.movieRating)
        moviePopularity = findViewById(R.id.moviePopularity)
        movieVoteCount = findViewById(R.id.movieVoteCount)
        movieOverview = findViewById(R.id.movieOverview)
        contactUsTextView = findViewById(R.id.contactTextView)
    }

    fun initListeners(){
        contactUsTextView.setOnClickListener {
            startActivity(Intent(this, ContactUsActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        initComponents()
        initListeners()

        val movieData:MovieModel = getExtrasFromPrevActivity()
        movieTitle.text = movieData.title
        movieReleaseDate.text = movieData.release_date
        movieRating.text = String.format("%.1f",movieData.vote_average)
        moviePopularity.text = movieData.popularity.toInt().toString()
        movieVoteCount.text = movieData.vote_count.toString()
        movieOverview.text = movieData.overview

        Picasso
            .get()
            .load("https://image.tmdb.org/t/p/original"+movieData.poster_path)
            .placeholder(R.drawable.no_img)
            .error(R.drawable.no_img)
            .into(movieThumbnail)
    }

    fun getExtrasFromPrevActivity():MovieModel{
        val bundle:Bundle = intent.extras!!
        return MovieModel(
            bundle.getBoolean("movieAdult"),
            bundle.getInt("movieId"),
            bundle.getString("movieTitle").toString(),
            bundle.getString("movieOverview").toString(),
            bundle.getFloat("moviePopularity"),
            bundle.getString("moviePosterPath").toString(),
            bundle.getString("movieReleaseDate").toString(),
            bundle.getFloat("movieVoteAverage"),
            bundle.getInt("movieVoteCount")
            )
    }
}