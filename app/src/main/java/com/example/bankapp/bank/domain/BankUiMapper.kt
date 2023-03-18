package com.example.bankapp.bank.domain

import com.example.bankapp.bank.presentation.BankUi

class BankUiMapper : BinItem.Mapper<BankUi> {
    override fun map(binNumber: String) = BankUi(binNumber)
}