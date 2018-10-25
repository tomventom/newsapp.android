package com.tomventura.newsapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(url: String, roundedCorners: Boolean = false) {
    val req = Glide.with(this).load(url)
    if (roundedCorners) req.apply(RequestOptions().transform(MultiTransformation(CenterCrop(), RoundedCorners(6))))
    req.into(this)
}