package com.example.bankapp.bank.presentation

sealed class UiState {

    interface Mapper<T> {
        fun map(message: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    class Success() : UiState() {
        override fun <T> map(mapper: Mapper<T>) = mapper.map("")
    }

    class Error(private val message: String) : UiState() {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(message)
    }
}