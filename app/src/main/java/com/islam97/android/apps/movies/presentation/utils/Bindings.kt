package com.islam97.android.apps.movies.presentation.utils

import android.graphics.drawable.LayerDrawable
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.islam97.android.apps.movies.R
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
    val progress = rating?.let {
        (it * 10).roundToInt()
    } ?: 0

    progressBar.progress = progress

    val color = ContextCompat.getColor(
        progressBar.context, when (progress) {
            in 0..29 -> R.color.progressColorLow
            in 30..69 -> R.color.progressColorMedium
            else -> R.color.progressColorHigh
        }
    )

    val progressDrawable = progressBar.progressDrawable
    if (progressDrawable is LayerDrawable) {
        progressDrawable.findDrawableByLayerId(R.id.progress_indicator).setTint(color)
    }
}

@BindingAdapter(value = ["rating"])
fun rating(textView: TextView, rating: Double?) {
    textView.text = rating?.let {
        "${((it * 10).roundToInt()) / 10f}"
    } ?: "0.0"
}

@BindingAdapter(value = ["movieDetails"])
fun movieDetails(textView: TextView, movie: Movie?) {
    textView.text = StringBuilder().append(getReleaseDate(movie?.releaseDate)).append(" | ")
        .append(movie?.originalLanguage?.uppercase()).toString()
}


private fun loadImage(imageView: ImageView, url: String) {
    Glide.with(imageView).load(url).placeholder(R.mipmap.placeholder).into(imageView)
}

fun getReleaseDate(releaseDate: String?): String {
    return releaseDate?.let { dateString ->
        try {
            SimpleDateFormat("yyyy-MM-dd", Locale("en")).parse(dateString)?.let { date ->
                SimpleDateFormat("MMM dd, yyyy", Locale("en")).format(date)
            } ?: ""
        } catch (e: Exception) {
            ""
        }
    } ?: ""
}