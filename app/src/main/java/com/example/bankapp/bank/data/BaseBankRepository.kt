package com.example.bankapp.bank.data

import com.example.bankapp.bank.data.cache.BankCacheDataSource
import com.example.bankapp.bank.data.cloud.BankCloudDataSource
import com.example.bankapp.bank.domain.BankRepository
import com.example.bankapp.bank.domain.BinItem

class BaseBankRepository(
    private val cloudDataSource: BankCloudDataSource,
    private val cacheDataSource: BankCacheDataSource,
    private val handleDataRequest: HandleDataRequest,
    private val mapperToDomain: BankData.Mapper<BinItem>
) : BankRepository {

    override suspend fun allBins(): List<BinItem> {
        val data = cacheDataSource.allBins()
        return data.map { it.map(mapperToDomain) }
    }

    override suspend fun bin(number: String) = handleDataRequest.handle {
        val dataSource = if (cacheDataSource.contains(number))
            cacheDataSource
        else
            cloudDataSource
        dataSource.fetch(number)
    }

}