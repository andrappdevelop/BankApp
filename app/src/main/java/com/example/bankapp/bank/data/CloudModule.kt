package com.example.bankapp.bank.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CloudModule {

    fun <T> service(clazz: Class<T>): T

    class Base() : CloudModule {
        override fun <T> service(clazz: Class<T>): T {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(clazz)
        }

        companion object {
            private const val BASE_URL = "https://lookup.binlist.net"
        }
    }
}