package com.tomventura.newsapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomventura.newsapp.Article
import com.tomventura.newsapp.R
import com.tomventura.newsapp.util.inflate
import kotlinx.android.synthetic.main.article_item.view.*

class NewsAdapter(private val articles: ArrayList<Article>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.article_item))
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = articles[holder.adapterPosition].title
        holder.description.text = articles[holder.adapterPosition].description

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title!!
        val description = itemView.description!!
        val image = itemView.image!!
    }

}