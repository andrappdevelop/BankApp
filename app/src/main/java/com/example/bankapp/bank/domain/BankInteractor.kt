package com.example.bankapp.bank.domain

interface BankInteractor {

    suspend fun init(): BankResult

    suspend fun fetch(number: String): BankResult
}