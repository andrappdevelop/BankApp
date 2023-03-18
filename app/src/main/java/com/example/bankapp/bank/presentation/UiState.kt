package com.example.bankapp.bank.presentation

sealed class UiState {

    class Success() : UiState() {

    }

    class Error(private val message: String) : UiState() {

    }
}