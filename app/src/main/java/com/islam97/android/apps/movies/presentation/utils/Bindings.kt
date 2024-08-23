package com.islam97.android.apps.movies.presentation.utils

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt


@BindingAdapter(value = ["moviePoster"])
fun moviePoster(loadImage: ImageView, url: String?) {
    loadImage(loadImage, "https://image.tmdb.org/t/p/w300_and_h450_bestv2/$url")
}

@BindingAdapter(value = ["movieReleaseDate"])
fun movieReleaseDate(textView: TextView, releaseDate: String?) {
    textView.text = releaseDate?.let {dateString ->
        try {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale("en")).parse(dateString)
           SimpleDateFormat("MMM dd, yyyy", Locale("en")).format(date)
        }catch (e:Exception){ "" }
    }?:""
}

@BindingAdapter(value = ["voteProgress"])
fun voteProgress(view: ProgressBar, voteAverage: Double?) {
    view.progress = voteAverage?.let {
        (it * 10).roundToInt()
    } ?: 0

}

@BindingAdapter(value = ["vote"])
fun vote(textView: TextView, vote: Double?) {
    textView.text = vote?.let {
        "${((it * 10).roundToInt()) / 10f}"
    } ?: "0.0"
}

private fun loadImage(imageView: ImageView, url: String) {
    Glide.with(imageView).load(url).into(imageView)
}