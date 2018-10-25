package com.tomventura.newsapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomventura.newsapp.data.Article
import com.tomventura.newsapp.R
import com.tomventura.newsapp.util.getRelativeTimeAgo
import com.tomventura.newsapp.util.inflate
import com.tomventura.newsapp.util.load
import kotlinx.android.synthetic.main.article_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(private val articles: ArrayList<Article>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(parent.inflate(R.layout.article_item))
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = articles[holder.adapterPosition].title
//        holder.category.text = articles[holder.adapterPosition].description
        holder.source.text = articles[holder.adapterPosition].author
        holder.time.text =
                if (articles[holder.adapterPosition].publishedAt.isEmpty()) ""
                else SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss'Z'",
                    Locale.getDefault()
                ).parse(articles[holder.adapterPosition].publishedAt).getRelativeTimeAgo()

        holder.image.load(articles[holder.adapterPosition].urlToImage, true)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title!!
        val image = itemView.image!!
        val source = itemView.source!!
        val time = itemView.time!!
    }

}

