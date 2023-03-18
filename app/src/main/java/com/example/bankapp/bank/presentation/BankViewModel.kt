package com.example.bankapp.bank.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankapp.R
import com.example.bankapp.bank.domain.BankInteractor
import com.example.bankapp.bank.domain.BankResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BankViewModel(
    private val dispatchers: DispatchersList,
    private val manageResources: ManageResources,
    private val communications: BankCommunication,
    private val interactor: BankInteractor,
    private val bankResultMapper: BankResult.Mapper<Unit>
) : FetchBin, ObserveBank, ViewModel() {

    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Boolean>) =
        communications.observeProgress(owner, observer)

    override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) =
        communications.observeState(owner, observer)

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<BankUi>>) =
        communications.observeList(owner, observer)

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            communications.showProgress(true)
            viewModelScope.launch(dispatchers.io()) {
                val result = interactor.init()
                communications.showProgress(false)
                result.map(bankResultMapper)
            }
        }
    }

    override fun fetch(number: String) {
        if (number.isEmpty()) {
            communications.showState(
                UiState.Error(manageResources.string(R.string.empty_number_error_message))
            )
        } else {
            communications.showProgress(true)
            viewModelScope.launch(dispatchers.io()) {
                val result = interactor.fetch(number)
                communications.showProgress(false)
                result.map(bankResultMapper)
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