package com.example.bankapp.bank.data

data class BankData(
    private val number: String,
    private val info: BinCloud
) {

    interface Mapper<T> {
        fun map(number: String, info: BinCloud): T

        class MatchesNumber(private val number: String) : Mapper<Boolean> {
            override fun map(number: String, info: BinCloud) = this.number == number
        }

        class Matches(private val data: BankData) : Mapper<Boolean> {
            override fun map(number: String, info: BinCloud): Boolean = data.number == number
        }
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(number, info)
}
