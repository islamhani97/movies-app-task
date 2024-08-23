package com.islam97.android.apps.movies.domain.models

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: T?,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int?
)