package com.example.bankapp.bank.presentation

import android.widget.TextView
import com.example.bankapp.bank.data.BinCloud

data class BankUi(
    private val number: String,
    private val binInfo: BinCloud
) : Mapper<Boolean, BankUi> {

    fun map(number: TextView) {
        number.text = this.number
    }

    override fun map(source: BankUi) = source.number == number
}