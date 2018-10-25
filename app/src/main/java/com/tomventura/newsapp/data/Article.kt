package com.tomventura.newsapp.data

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

data class Article(@SerializedName("title") private val _title: String? = "",
                   @SerializedName("description") private val _description: String? = "",
                   @SerializedName("content") private val _content: String? = "",
                   @SerializedName("urlToImage") private val _urlToImage: String? = "",
                   @SerializedName("publishedAt") private val _publishedAt: String? ) {

    val title
        get() = _title ?: ""

    val description
        get() = _description ?: ""

    val content
        get() = _content ?: ""

    val urlToImage
        get() = _urlToImage ?: ""

    var author = ""

    val publishedAt
        get() = _publishedAt ?: ""

}