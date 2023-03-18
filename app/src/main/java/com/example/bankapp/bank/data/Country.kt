package com.example.bankapp.bank.data

data class Country(
    private val numeric: String = "",
    private val alpha2: String = "",
    private val name: String = "",
    private val emoji: String = "",
    private val currency: String = "",
    private val latitude: Int? = null,
    private val longitude: Int? = null
)
