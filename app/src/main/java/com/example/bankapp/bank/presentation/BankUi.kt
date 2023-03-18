package com.example.bankapp.bank.presentation

import android.widget.TextView

data class BankUi(
    private val binNumber: String
) {

    fun map(number: TextView) {
        number.text = binNumber
    }
}