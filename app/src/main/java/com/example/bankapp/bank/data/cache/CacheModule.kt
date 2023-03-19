package com.example.bankapp.bank.data.cache

import android.content.Context
import androidx.room.Room

interface CacheModule {

    fun provideDataBase(): BankDatabase

    class Base(private val context: Context) : CacheModule {

        private val database by lazy {
            return@lazy Room.databaseBuilder(
                context,
                BankDatabase::class.java,
                "bank_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        override fun provideDataBase(): BankDatabase = database
    }

    class Mock(private val context: Context) : CacheModule {
        private val database by lazy {
            Room.inMemoryDatabaseBuilder(context, BankDatabase::class.java)
                .build()
        }

        override fun provideDataBase(): BankDatabase = database
    }
}