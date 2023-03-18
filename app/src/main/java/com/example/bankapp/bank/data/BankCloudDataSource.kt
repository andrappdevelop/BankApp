package com.example.bankapp.bank.data

interface BankCloudDataSource {

    suspend fun fetch(number: String): BankData
}