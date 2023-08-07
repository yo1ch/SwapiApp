package com.example.core.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface NetworkApi {
    fun getRetrofit(): Retrofit
    fun getOkhttp(): OkHttpClient
    fun getImageLoader(): ImageLoader
}