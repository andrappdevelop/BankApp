package com.example.bankapp.bank.domain

sealed class BankResult {

    interface Mapper<T> {
        fun map(list: List<BinItem>, errorMessage: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    class Success(private val list: List<BinItem>) : BankResult() {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(list, "")
    }

    class Failure(private val message: String) : BankResult() {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(emptyList(), message)
    }
}