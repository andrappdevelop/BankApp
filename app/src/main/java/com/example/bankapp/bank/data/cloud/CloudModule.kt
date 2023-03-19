package com.example.bankapp.bank.data.cloud

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CloudModule {

    fun <T> service(clazz: Class<T>): T

    class Base() : CloudModule {
        override fun <T> service(clazz: Class<T>): T {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(clazz)
        }

        companion object {
            private const val BASE_URL = "https://lookup.binlist.net"
        }
    }
}