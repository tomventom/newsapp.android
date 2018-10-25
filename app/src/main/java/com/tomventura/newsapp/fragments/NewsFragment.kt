package com.tomventura.newsapp.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.tomventura.newsapp.Article

import com.tomventura.newsapp.R
import com.tomventura.newsapp.adapters.NewsAdapter
import com.tomventura.newsapp.util.Constants
import com.tomventura.newsapp.util.inflate
import kotlinx.android.synthetic.main.fragment_news.*
import org.json.JSONObject


class NewsFragment : Fragment() {

    private val articles: ArrayList<Article> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return container?.inflate(R.layout.fragment_news)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvNews.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvNews.adapter = NewsAdapter(articles)
        getNews()
    }

    fun getNews() {
        "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=${Constants.NEWS_API_KEY}".httpGet().responseString {request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    ex.printStackTrace()
                }
                is Result.Success -> {
                    val data = result.get()
                    Log.i("NewsFragment", data)
                    val obj = JSONObject(data)
                    val arr = obj.getJSONArray("articles")
                    for (i in 0 until arr.length()) {
                        articles.add(Gson().fromJson(arr[i].toString(), Article::class.java))
                    }

                    rvNews.adapter!!.notifyDataSetChanged()
                }
            }
        }
    }


}
