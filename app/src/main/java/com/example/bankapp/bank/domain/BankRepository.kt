package com.example.bankapp.bank.domain

interface BankRepository {

    suspend fun allBins(): List<BinItem>

    suspend fun bin(number: String): BinItem
}