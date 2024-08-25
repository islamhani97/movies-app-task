package com.islam97.android.apps.movies.presentation.utils

import android.graphics.drawable.Drawable
import android.view.View
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.islam97.android.apps.movies.R
import com.islam97.android.apps.movies.databinding.LoadingLayoutBinding

fun LoadingLayoutBinding.setLoading(loading: Boolean) {
    if (loading) {
        val animated =
            AnimatedVectorDrawableCompat.create(root.context, R.drawable.loading_animation)
        animated?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                animated.start()
            }
        })
        progressIv.setImageDrawable(animated)
        animated?.start()
        root.visibility = View.VISIBLE
    } else {
        root.visibility = View.GONE
        progressIv.setImageDrawable(null)
    }
}