package com.example.bankapp.bank.domain

data class Number(
    private val length: Int? = null,
    private val luhn: Boolean = false
)
