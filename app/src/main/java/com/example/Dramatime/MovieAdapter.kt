package com.christianrondonuwu.daftarfilmku

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Dramatime.MovieDetailsActivity
import com.example.Dramatime.R
import com.example.Dramatime.models.MovieModel
import com.squareup.picasso.Picasso

class MovieAdapter(private val movieList:ArrayList<MovieModel>,private val context: Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val movieListItem:RelativeLayout = itemView.findViewById(R.id.movieListItemRL)
        val movieThumbnail:ImageView = itemView.findViewById(R.id.movieThumbnail)
        val movieTitle:TextView = itemView.findViewById(R.id.movieTitle)
        val movieReleaseDate:TextView = itemView.findViewById(R.id.movieReleaseDate)
        val movieRating:TextView = itemView.findViewById(R.id.movieRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = movieList[position]
        val movieRating = String.format("%.1f",currentMovie.vote_average)

        holder.movieTitle.text = currentMovie.title
        holder.movieReleaseDate.text = currentMovie.release_date
        holder.movieRating.text = movieRating

        Picasso.get()
            .load("https://image.tmdb.org/t/p/original"+currentMovie.poster_path)
            .placeholder(R.drawable.no_img)
            .error(R.drawable.no_img)
            .into(holder.movieThumbnail)

        // Set OnClickListener if needed
        holder.movieListItem.setOnClickListener {
            val intent:Intent = Intent(context, MovieDetailsActivity::class.java)
            val bundle:Bundle = Bundle()
            bundle.putString("movieTitle",currentMovie.title)
            bundle.putString("movieOverview",currentMovie.overview)
            bundle.putString("movieReleaseDate",currentMovie.release_date)
            bundle.putString("moviePosterPath",currentMovie.poster_path)
            bundle.putFloat("movieVoteAverage",currentMovie.vote_average)
            bundle.putFloat("moviePopularity",currentMovie.popularity)
            bundle.putInt("movieVoteCount",currentMovie.vote_count)
            bundle.putInt("movieId",currentMovie.id)
            bundle.putBoolean("movieAdult",currentMovie.adult)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = movieList.size
}