package com.example.bankapp.bank.data

import com.example.bankapp.bank.domain.BankRepository
import com.example.bankapp.bank.domain.BinItem
import com.example.bankapp.bank.domain.HandleError

class BaseBankRepository(
    private val cloudDataSource: BankCloudDataSource,
    private val cacheDataSource: BankCacheDataSource,
    private val mapperToDomain: BankData.Mapper<BinItem>,
    private val handleError: HandleError<Exception>
) : BankRepository {

    override suspend fun allBins(): List<BinItem> {
        val data = cacheDataSource.allBins()
        return data.map { it.map(mapperToDomain) }
    }

    override suspend fun bin(number: String) =
        if (cacheDataSource.contains(number)) {
            val bankData = cacheDataSource.fetch(number)
            cacheDataSource.saveFetch(bankData)
            bankData.map(mapperToDomain)
        } else try {
            val result = cloudDataSource.fetch(number)
            cacheDataSource.saveFetch(result)
            result.map(mapperToDomain)
        } catch (e: Exception) {
            throw handleError.handle(e)
        }
}