package com.islam97.android.apps.movies.presentation.ui.home

import com.islam97.android.apps.movies.R
import com.islam97.android.apps.movies.databinding.FragmentHomeBinding
import com.islam97.android.apps.movies.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layout: Int
        get() = R.layout.fragment_home
}