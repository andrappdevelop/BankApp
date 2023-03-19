package com.example.bankapp.bank.domain

import com.example.bankapp.bank.presentation.BankUi

class BankUiMapper : BinItem.Mapper<BankUi> {
    override fun map(number: String, binInfo: String) = BankUi(number, binInfo)
}