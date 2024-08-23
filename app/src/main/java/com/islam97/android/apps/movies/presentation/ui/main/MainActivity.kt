package com.islam97.android.apps.movies.presentation.ui.main

import com.islam97.android.apps.movies.R
import com.islam97.android.apps.movies.databinding.ActivityMainBinding
import com.islam97.android.apps.movies.presentation.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layout: Int
        get() = R.layout.activity_main
}