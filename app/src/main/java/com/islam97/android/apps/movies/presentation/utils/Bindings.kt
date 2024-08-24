package com.islam97.android.apps.movies.presentation.utils

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.islam97.android.apps.movies.domain.models.Movie
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt


@BindingAdapter(value = ["moviePoster"])
fun moviePoster(loadImage: ImageView, url: String?) {
    loadImage(loadImage, "https://image.tmdb.org/t/p/w300_and_h450_bestv2/$url")
}

@BindingAdapter(value = ["movieReleaseDate"])
fun movieReleaseDate(textView: TextView, releaseDate: String?) {
    textView.text = getReleaseDate(releaseDate)
}

@BindingAdapter(value = ["ratingProgress"])
fun ratingProgress(progressBar: ProgressBar, rating: Double?) {
    progressBar.progress = rating?.let {
        (it * 10).roundToInt()
    } ?: 0

}

@BindingAdapter(value = ["rating"])
fun rating(textView: TextView, rating: Double?) {
    textView.text = rating?.let {
        "${((it * 10).roundToInt()) / 10f}"
    } ?: "0.0"
}

@BindingAdapter(value = ["movieDetails"])
fun movieDetails(textView: TextView, movie: Movie?) {
    textView.text =
        "${getReleaseDate(movie?.releaseDate)} | ${movie?.originalLanguage?.uppercase()}"
}


private fun loadImage(imageView: ImageView, url: String) {
    Glide.with(imageView).load(url).into(imageView)
}

fun getReleaseDate(releaseDate: String?): String {
    return releaseDate?.let { dateString ->
        try {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale("en")).parse(dateString)
            SimpleDateFormat("MMM dd, yyyy", Locale("en")).format(date)
        } catch (e: Exception) {
            ""
        }
    } ?: ""
}