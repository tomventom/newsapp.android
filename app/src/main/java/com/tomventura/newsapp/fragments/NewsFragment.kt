package com.tomventura.newsapp.fragments


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.tomventura.newsapp.R
import com.tomventura.newsapp.adapters.NewsAdapter
import com.tomventura.newsapp.data.Article
import com.tomventura.newsapp.util.Constants
import com.tomventura.newsapp.util.inflate
import kotlinx.android.synthetic.main.fragment_news.*
import org.json.JSONObject


class NewsFragment : Fragment() {

    private val articles: ArrayList<Article> = ArrayList()
    private var searchView: SearchView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return container?.inflate(R.layout.fragment_news)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.search, menu)
        val searchMenuItem = menu!!.findItem(R.id.action_search)

        searchView = searchMenuItem?.actionView as SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getNews(searchView?.query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        searchView?.findViewById<View>(R.id.search_close_btn)?.setOnClickListener {
            searchView?.setQuery("", false)
            searchView?.isIconified = true
            searchMenuItem.collapseActionView()
            getNews("")
        }

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefreshLayout.setOnRefreshListener {getNews(searchView?.query.toString())}

        rvNews.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvNews.adapter = NewsAdapter(articles)
        getNews("")
    }

    fun getNews(query: String) {
        "https://newsapi.org/v2/everything?${if (!query.isEmpty()) "q=$query&" else ""}sources=ynet&apiKey=${Constants.NEWS_API_KEY}"
            .httpGet().responseString { _, _, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    ex.printStackTrace()
                }
                is Result.Success -> {
                    val data = result.get()
                    Log.i("NewsFragment", data)
                    val arr = JSONObject(data).getJSONArray("articles")
                    articles.clear()
                    for (i in 0 until arr.length()) {
                        val a = Gson().fromJson(arr[i].toString(), Article::class.java)
                        a.author = arr.getJSONObject(i).getJSONObject("source").optString("name") ?: ""
                        if (!a.urlToImage.isEmpty())
                            articles.add(a)
                    }

                    if (swipeRefreshLayout.isRefreshing) swipeRefreshLayout.isRefreshing = false
                    rvNews.adapter!!.notifyDataSetChanged()
                }
            }
        }
    }


}
