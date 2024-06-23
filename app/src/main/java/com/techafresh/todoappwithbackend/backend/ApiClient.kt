package com.techafresh.todoappwithbackend.backend

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    companion object {
        private val BASE_URL = "http://10.0.2.2:3060/"
        private val BASE_URdL = "http://192.168.0.120:3060/" // To run on a physical device ipv4 address of the router that both devices are connected to
        private var retrofit: Retrofit? = null

        fun getApiClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}