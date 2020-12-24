package com.example.newsapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.news.MySingleton
import com.example.news.News
import com.example.news.NewsItemClicked
import com.example.news.NewsListAdapter
import kotlin.collections.ArrayList as ArrayList

class worldnewsfront : AppCompatActivity(), NewsItemClicked {


    private lateinit var mAdapter: NewsListAdapter
    private lateinit var swipeContainer: SwipeRefreshLayout
    var url: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worldnewsfront)

        var category=intent.getStringExtra("pass")
        url= "https://newsapi.org/v2/top-headlines?$category&apiKey=d566207c37604c639b779406680bcef3"
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = NewsListAdapter(this)
        recyclerView.adapter = mAdapter
        swipeContainer= findViewById(R.id.swipeContainer)

        swipeContainer.setOnRefreshListener {
                fetchData()
                swipeContainer.isRefreshing=false

        }
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);
    }

        private fun fetchData() {
            //volly library

            //making a request
            val jsonObjectRequest = object : JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener {
                    val newsJsonArray = it.getJSONArray("articles")
                    val newsArray = ArrayList<News>()
                    newsArray.clear()
                    for (i in 0 until newsJsonArray.length()) {
                        val newsJsonObject = newsJsonArray.getJSONObject(i)
                        val news = News(
                            newsJsonObject.getString("title"),
                            newsJsonObject.getString("author"),
                            newsJsonObject.getString("url"),
                            newsJsonObject.getString("urlToImage")
                        )
                        newsArray.add(news)
                    }
                    mAdapter.updateNews(newsArray)
                },
                Response.ErrorListener {
                    Toast.makeText(this, "Error Fetching News", Toast.LENGTH_LONG).show()
                }

            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["User-Agent"] = "Mozilla/5.0"
                    return headers
                }
            }

            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }

    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }
}