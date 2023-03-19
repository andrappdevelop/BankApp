package com.example.bankapp.main.sl

import android.content.Context
import com.example.bankapp.bank.data.cache.CacheModule
import com.example.bankapp.bank.data.cloud.CloudModule

interface ProvideInstances {
    fun provideCloudModule(): CloudModule
    fun provideCacheModule(): CacheModule

    class Release(private val context: Context) : ProvideInstances {
        override fun provideCloudModule() = CloudModule.Base()
        override fun provideCacheModule() = CacheModule.Base(context)
    }
}