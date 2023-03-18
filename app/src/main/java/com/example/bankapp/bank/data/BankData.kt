package com.example.bankapp.bank.data

data class BankData(private val number: String) {

    interface Mapper<T> {
        fun map(number: String): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(number)
}
