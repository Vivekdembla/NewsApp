package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R

class NewsListAdapter(private val listener: NewsItemClicked) :
    RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()
    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(list: List<News>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)

        val viewHolder = NewsViewHolder(view)

        view.setOnClickListener {

            listener.onItemClicked(items[viewHolder.adapterPosition])

        }
        return viewHolder

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.author.text= currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }

    fun updateNews(updatedNews: ArrayList<News>) {
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()

    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val author=itemView.findViewById<TextView>(R.id.author)
    val image= itemView.findViewById<ImageView>(R.id.image)
}

interface NewsItemClicked {
    fun onItemClicked(item: News)
}