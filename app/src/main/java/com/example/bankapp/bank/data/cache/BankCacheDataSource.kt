package com.example.bankapp.bank.data.cache

import com.example.bankapp.bank.data.BankData
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface BankCacheDataSource : FetchBank {

    suspend fun allBins(): List<BankData>

    suspend fun contains(number: String): Boolean

    suspend fun saveFetch(bankData: BankData)

    class Base(
        private val dao: BankDao, private val dataToCache: BankData.Mapper<BankCache>
    ) : BankCacheDataSource {

        private val mutex = Mutex()

        override suspend fun allBins(): List<BankData> = mutex.withLock {
            val data = dao.allBins()
            return data.map { BankData(it.number, it.binInfo) }
        }

        override suspend fun contains(number: String): Boolean = mutex.withLock {
            val data = dao.fetch(number)
            return data != null
        }

        override suspend fun fetch(number: String): BankData = mutex.withLock {
            val bankCache = dao.fetch(number) ?: BankCache("", "", 0)
            return BankData(bankCache.number, bankCache.binInfo)
        }

        override suspend fun saveFetch(bankData: BankData) = mutex.withLock {
            dao.insert(bankData.map(dataToCache))
        }
    }
}

interface FetchBank {
    suspend fun fetch(number: String): BankData
}
