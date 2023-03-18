package com.example.bankapp.bank.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankapp.R
import com.example.bankapp.bank.domain.BankInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class BankViewModel(
    private val handleResult: HandleBankRequest,
    private val manageResources: ManageResources,
    private val communications: BankCommunication,
    private val interactor: BankInteractor
) : FetchBin, ObserveBank, ViewModel() {

    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Boolean>) =
        communications.observeProgress(owner, observer)

    override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) =
        communications.observeState(owner, observer)

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<BankUi>>) =
        communications.observeList(owner, observer)

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            handleResult.handle(viewModelScope) {
                interactor.init()
            }
        }
    }

    override fun fetch(number: String) {
        if (number.isEmpty()) {
            communications.showState(
                UiState.Error(manageResources.string(R.string.empty_number_error_message))
            )
        } else {
            handleResult.handle(viewModelScope) {
                interactor.fetch(number)
            }
        }
    }
}

interface FetchBin {

    fun init(isFirstRun: Boolean)

    fun fetch(number: String)
}

interface DispatchersList {

    fun io(): CoroutineDispatcher

    fun ui(): CoroutineDispatcher

    class Base : DispatchersList {
        override fun io(): CoroutineDispatcher = Dispatchers.IO
        override fun ui(): CoroutineDispatcher = Dispatchers.Main
    }
}