package com.example.bankapp.bank.data.cache

import com.example.bankapp.bank.data.BankData

class BankDataToCache : BankData.Mapper<BankCache> {
    override fun map(number: String, info: String) =
        BankCache(number, info, System.currentTimeMillis())
}