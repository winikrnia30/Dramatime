package com.example.Dramatime

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.christianrondonuwu.daftarfilmku.MovieAdapter
import com.example.Dramatime.retrofit.ApiService
import com.example.Dramtime.models.GetMovieResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BerandaActivity : AppCompatActivity() {

    private lateinit var movieListRecyclerView:RecyclerView
    private lateinit var bannerTitleTextView:TextView
    private lateinit var searchEditText:EditText
    private lateinit var contachUsTextView:TextView

    fun initComponents(){
        movieListRecyclerView = findViewById(R.id.movieListRecyclerView)
        bannerTitleTextView = findViewById(R.id.bannerTitleTextView)
        searchEditText = findViewById(R.id.searchEditText)
        contachUsTextView = findViewById(R.id.contactTextView)
    }

    fun initListeners(){
        searchEditText.addTextChangedListener(object:TextWatcher{
            var isSearch:Boolean = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val queryLength = s!!.length
                if(queryLength > 1){
                    searchMoviesAndSetView(s.toString())
                    if(!isSearch){
                        isSearch = true
                        bannerTitleTextView.text = "Cari Film"
                    }
                }else if(queryLength <= 1 && isSearch){
                    getPopularMoviesAndSetView()
                    isSearch = false
                    bannerTitleTextView.text = "20 FILM POPULER"
                }
            }
        })

        contachUsTextView.setOnClickListener {
            startActivity(Intent(this,ContactUsActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        initComponents()
        initListeners()

        getPopularMoviesAndSetView()
    }

    fun getPopularMoviesAndSetView(){
        ApiService.endpoint.getPopularMovie().enqueue(object : Callback<GetMovieResponseModel> {
            override fun onResponse(
                call: Call<GetMovieResponseModel>,
                response: Response<GetMovieResponseModel>
            ) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    val movies = body.results

                    val adapter = MovieAdapter(movies,this@BerandaActivity)
                    movieListRecyclerView.adapter = adapter
                    movieListRecyclerView.layoutManager = LinearLayoutManager(this@BerandaActivity)
                }
            }

            override fun onFailure(call: Call<GetMovieResponseModel>, t: Throwable) {
                Log.d("API",t.toString())
            }

        })
    }

    fun searchMoviesAndSetView(query:String){
        ApiService.endpoint.searchMovie(query).enqueue(object : Callback<GetMovieResponseModel>{
            override fun onResponse(
                call: Call<GetMovieResponseModel>,
                response: Response<GetMovieResponseModel>
            ) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    val movies = body.results

                    val adapter = MovieAdapter(movies,this@BerandaActivity)
                    movieListRecyclerView.adapter = adapter
                    movieListRecyclerView.layoutManager = LinearLayoutManager(this@BerandaActivity)
                }
            }

            override fun onFailure(call: Call<GetMovieResponseModel>, t: Throwable) {
                Log.d("API",t.toString())
            }

        })
    }
}