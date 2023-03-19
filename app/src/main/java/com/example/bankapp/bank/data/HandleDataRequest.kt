package com.example.bankapp.bank.data

import com.example.bankapp.bank.data.cache.BankCacheDataSource
import com.example.bankapp.bank.domain.BinItem
import com.example.bankapp.bank.domain.HandleError

interface HandleDataRequest {

    suspend fun handle(block: suspend () -> BankData): BinItem

    class Base(
        private val cacheDataSource: BankCacheDataSource,
        private val mapperToDomain: BankData.Mapper<BinItem>,
        private val handleError: HandleError<Exception>
    ) : HandleDataRequest {

        override suspend fun handle(block: suspend () -> BankData) = try {
            val result = block.invoke()
            cacheDataSource.saveFetch(result)
            result.map(mapperToDomain)
        } catch (e: Exception) {
            throw handleError.handle(e)
        }
    }
}