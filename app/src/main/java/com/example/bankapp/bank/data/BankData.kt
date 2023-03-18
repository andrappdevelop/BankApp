package com.example.bankapp.bank.data

data class BankData(
    private val number: String,
    private val info: BinCloud
) {

    interface Mapper<T> {
        fun map(number: String, info: BinCloud): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(number, info)
}
