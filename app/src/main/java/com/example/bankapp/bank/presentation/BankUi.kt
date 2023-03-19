package com.example.bankapp.bank.presentation

import android.widget.TextView

data class BankUi(
    private val number: String,
    private val binInfo: String
) : Mapper<Boolean, BankUi> {

    fun uiNumber() = number

    fun <T> map(mapper: Mapper<T>): T = mapper.map(number, binInfo)

    interface Mapper<T> {
        fun map(number: String, binInfo: String): T
    }

    fun map(number: TextView) {
        number.text = this.number
    }

    override fun map(source: BankUi) = source.number == number
}

class ListItemUi(
    private val title: TextView,
    private val subtitle: TextView
) : BankUi.Mapper<Unit> {

    override fun map(number: String, binInfo: String) {
        title.text = number
        subtitle.text = binInfo
    }
}