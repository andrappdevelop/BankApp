package com.example.bankapp.main.sl

import android.content.Context
import com.example.bankapp.bank.data.cache.BankDatabase
import com.example.bankapp.bank.data.cache.CacheModule
import com.example.bankapp.bank.data.cloud.CloudModule
import com.example.bankapp.bank.presentation.DispatchersList
import com.example.bankapp.bank.presentation.ManageResources

interface Core : CloudModule, CacheModule, ManageResources {

    fun provideDispatchers(): DispatchersList

    class Base(
        context: Context,
        private val provideInstances: ProvideInstances
    ) : Core {

        private val manageResources: ManageResources = ManageResources.Base(context)

        private val dispatchersList by lazy {
            DispatchersList.Base()
        }

        private val cloudModule by lazy {
            provideInstances.provideCloudModule()
        }

        private val cacheModule by lazy {
            provideInstances.provideCacheModule()
        }

        override fun <T> service(clazz: Class<T>): T = cloudModule.service(clazz)

        override fun provideDataBase() = cacheModule.provideDataBase()

        override fun string(id: Int) = manageResources.string(id)

        override fun provideDispatchers() = dispatchersList
    }
}