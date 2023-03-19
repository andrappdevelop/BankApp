package com.example.bankapp.bank.data.cloud

import com.example.bankapp.bank.data.BankData
import com.example.bankapp.bank.data.cache.FetchBank

interface BankCloudDataSource : FetchBank {

    class Base(
        private val service: BankService
    ) : BankCloudDataSource {
        override suspend fun fetch(number: String): BankData {
            val binInfo = service.binInfo(number)
            return BankData(number/*, binInfo*/)
        }
    }
}