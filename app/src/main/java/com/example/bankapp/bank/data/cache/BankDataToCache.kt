package com.example.bankapp.bank.data.cache

import com.example.bankapp.bank.data.BankData
import com.example.bankapp.bank.data.BinCloud

class BankDataToCache : BankData.Mapper<BankCache> {
    override fun map(number: String/*, info: BinCloud*/) =
        BankCache(number,/* info,*/ System.currentTimeMillis())
}