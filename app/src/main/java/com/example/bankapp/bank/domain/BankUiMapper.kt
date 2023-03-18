package com.example.bankapp.bank.domain

import com.example.bankapp.bank.data.BinCloud
import com.example.bankapp.bank.presentation.BankUi

class BankUiMapper : BinItem.Mapper<BankUi> {
    override fun map(number: String, binInfo: BinCloud) = BankUi(number, binInfo)
}