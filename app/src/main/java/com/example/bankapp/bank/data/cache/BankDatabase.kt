package com.example.bankapp.bank.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BankCache::class], version = 1)
abstract class BankDatabase : RoomDatabase() {

    abstract fun bankDao(): BankDao
}