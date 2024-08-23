package com.islam97.android.apps.movies.presentation.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.islam97.android.apps.movies.data.network.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        /** BASE_URL for API requests.
         * To make it more consistent we can add it to build file to have the ability to change
         * BASE_URL depends on build types and flavours. */
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(Interceptor {
            val request: Request = it.request()
            val url = request.url().newBuilder()
                /** API_KEY for the requests.
                 * It's not a best practice to expose API_KEY hard coded,
                 * but this is a demo project for proof of concept. */
                .addQueryParameter("api_key", "a7aa777890be978896f11990f0e5bbb7").build()
            val newRequest = request.newBuilder().url(url).build()
            it.proceed(newRequest)
        }).build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Singleton
    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }
}