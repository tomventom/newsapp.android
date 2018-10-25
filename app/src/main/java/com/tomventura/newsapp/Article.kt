package com.tomventura.newsapp

import com.google.gson.annotations.SerializedName

data class Article(@SerializedName("title") private val _title: String? = "",
                   @SerializedName("description") private val _description: String? = "",
                   @SerializedName("content") private val _content: String? = "",
                   @SerializedName("urlToImage") private val _urlToImage: String? = "") {

    val title
        get() = _title ?: ""

    val description
        get() = _description ?: ""

    val content
        get() = _content ?: ""

    val urlToImage
        get() = _urlToImage ?: ""

}