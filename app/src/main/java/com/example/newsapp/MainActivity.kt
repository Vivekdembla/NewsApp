package com.example.newsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun worldNews(view: View) {
        val intent= Intent(this,worldnewsfront::class.java)
        val pass="sources=bbc-news"
        intent.putExtra("pass",pass)
        startActivity(intent)
    }

    fun sportsnews(view: View) {
        val intent= Intent(this,worldnewsfront::class.java)
        val pass="country=in&category=sports"
        intent.putExtra("pass",pass)
        startActivity(intent)
    }
    fun sciencenews(view: View) {
        val intent= Intent(this,worldnewsfront::class.java)
        val pass="country=in&category=science"
        intent.putExtra("pass",pass)
        startActivity(intent)
    }
    fun healthnews(view: View) {
        val intent= Intent(this,worldnewsfront::class.java)
        val pass="country=in&category=health"
        intent.putExtra("pass",pass)
        startActivity(intent)
    }
    fun generalnews(view: View) {
        val intent= Intent(this,worldnewsfront::class.java)
        val pass="country=in&category=general"
        intent.putExtra("pass",pass)
        startActivity(intent)
    }
    fun businessnews(view: View) {
        val intent= Intent(this,worldnewsfront::class.java)
        val pass="country=in&category=business"
        intent.putExtra("pass",pass)
        startActivity(intent)
    }
    fun entertainmentnews(view: View) {
        val intent= Intent(this,worldnewsfront::class.java)
        val pass="country=in&category=entertainment"
        intent.putExtra("pass",pass)
        startActivity(intent)
    }
    fun technologynews(view: View) {
        val intent= Intent(this,worldnewsfront::class.java)
        val pass="country=in&category=technology"
        intent.putExtra("pass",pass)
        startActivity(intent)
    }

    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()
        val image=findViewById<ImageView>(R.id.image)
        val category=findViewById<TextView>(R.id.category)
        val mode = resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
        when (mode) {
            Configuration.UI_MODE_NIGHT_YES -> {
                image.setImageResource(R.color.black)
                category.setTextColor(ContextCompat.getColor(this,R.color.white))
            }
        }
    }
}