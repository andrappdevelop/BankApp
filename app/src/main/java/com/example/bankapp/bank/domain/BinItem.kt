package com.example.bankapp.bank.domain

data class BinItem(
    private val number: String,
    private val info: String
) {

    interface Mapper<T> {
        fun map(number: String, info: String): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(number, info)
}
