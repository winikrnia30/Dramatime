package com.example.Dramatime

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var startButton:Button

    fun initComponents(){
        startButton = findViewById(R.id.startButton)
    }

    fun initListeners(){
        startButton.setOnClickListener {
            startActivity(Intent(this,BerandaActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        initListeners()
    }
}