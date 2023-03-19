package com.example.bankapp.bank.sl

import com.example.bankapp.bank.data.BankDataToDomain
import com.example.bankapp.bank.data.BaseBankRepository
import com.example.bankapp.bank.data.HandleDataRequest
import com.example.bankapp.bank.data.HandleDomainError
import com.example.bankapp.bank.data.cache.BankCacheDataSource
import com.example.bankapp.bank.data.cache.BankDataToCache
import com.example.bankapp.bank.data.cloud.BankCloudDataSource
import com.example.bankapp.bank.data.cloud.BankService
import com.example.bankapp.bank.domain.BankInteractor
import com.example.bankapp.bank.domain.BankUiMapper
import com.example.bankapp.bank.domain.HandleError
import com.example.bankapp.bank.presentation.*
import com.example.bankapp.main.sl.Core
import com.example.bankapp.main.sl.Module

class BankModule(private val core: Core) : Module<BankViewModel> {
    override fun viewModel(): BankViewModel {
        val communications = BankCommunication.Base(
            ProgressCommunication.Base(),
            BankStateCommunication.Base(),
            BankListCommunication.Base()
        )
        val cacheDataSource = BankCacheDataSource.Base(
            core.provideDataBase().bankDao(),
            BankDataToCache()
        )
        val repository = BaseBankRepository(
            BankCloudDataSource.Base(core.service(BankService::class.java)),
            cacheDataSource,
            HandleDataRequest.Base(
                cacheDataSource,
                BankDataToDomain(),
                HandleDomainError()
            ),
            BankDataToDomain()
        )
        return BankViewModel(
            HandleBankRequest.Base(
                core.provideDispatchers(),
                communications,
                BankResultMapper(communications, BankUiMapper())
            ),
            core,
            communications,
            BankInteractor.Base(
                repository,
                HandleError.Base(core)
            )
        )
    }
}