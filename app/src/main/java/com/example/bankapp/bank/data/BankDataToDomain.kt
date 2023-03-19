package com.example.bankapp.bank.data

import com.example.bankapp.bank.domain.BinItem

class BankDataToDomain : BankData.Mapper<BinItem> {
    override fun map(number: String, info: String) = BinItem(number, info)
}