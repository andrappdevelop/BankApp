package com.example.bankapp.bank.domain

interface BankInteractor {

    suspend fun init(): BankResult

    suspend fun fetch(number: String): BankResult

    class Base(
        private val repository: BankRepository,
        private val handleError: HandleError
    ) : BankInteractor {

        override suspend fun init(): BankResult = BankResult.Success(repository.allBins())

        override suspend fun fetch(number: String): BankResult = try {
            repository.bin(number)
            BankResult.Success(repository.allBins())
        } catch (e: Exception) {
            BankResult.Failure(handleError.handle(e))
        }
    }
}