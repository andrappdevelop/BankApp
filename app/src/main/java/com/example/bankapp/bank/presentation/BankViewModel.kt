package com.example.bankapp.bank.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankapp.bank.domain.BankInteractor
import com.example.bankapp.bank.domain.BankResult
import kotlinx.coroutines.launch

class BankViewModel(
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
            viewModelScope.launch {
                val result = interactor.init()
                communications.showProgress(false)
                result.map(bankResultMapper)
            }
        }
    }

    override fun fetch(number: String) {
        TODO("Not yet implemented")
    }
}

interface FetchBin {

    fun init(isFirstRun: Boolean)

    fun fetch(number: String)
}