package com.example.bankapp.bank.domain

data class BinItem(
    private val binNumber: String
) {

    interface Mapper<T> {
        fun map(binNumber: String): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(binNumber)
}
