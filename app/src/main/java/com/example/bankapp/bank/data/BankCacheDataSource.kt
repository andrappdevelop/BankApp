package com.example.bankapp.bank.data

interface BankCacheDataSource {

    suspend fun allBins(): List<BankData>

    suspend fun contains(number: String): Boolean

    suspend fun fetch(number: String): BankData

    suspend fun saveFetch(bankData: BankData)
}