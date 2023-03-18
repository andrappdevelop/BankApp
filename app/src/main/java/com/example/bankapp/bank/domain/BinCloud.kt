package com.example.bankapp.bank.domain

data class BinCloud(
    private val number: Number = Number(),
    private val scheme: String = "",
    private val type: String = "",
    private val brand: String = "",
    private val prepaid: Boolean = false,
    private val country: Country = Country(),
    private val bank: Bank = Bank()
)