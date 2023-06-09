package com.example.bankapp.bank.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface BankCommunication : ObserveBank {

    fun showProgress(show: Int)

    fun showState(uiState: UiState)

    fun showList(list: List<BankUi>)

    class Base(
        private val progress: ProgressCommunication,
        private val state: BankStateCommunication,
        private val bankList: BankListCommunication
    ) : BankCommunication {

        override fun showProgress(show: Int) = progress.map(show)

        override fun showState(uiState: UiState) = state.map(uiState)

        override fun showList(list: List<BankUi>) = bankList.map(list)

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) =
            progress.observe(owner, observer)

        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) =
            state.observe(owner, observer)

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<BankUi>>) =
            bankList.observe(owner, observer)
    }
}

interface ObserveBank {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)

    fun observeState(owner: LifecycleOwner, observer: Observer<UiState>)

    fun observeList(owner: LifecycleOwner, observer: Observer<List<BankUi>>)
}

interface ProgressCommunication : Communication.Mutable<Int> {
    class Base() : Communication.Post<Int>(), ProgressCommunication
}

interface BankStateCommunication : Communication.Mutable<UiState> {
    class Base() : Communication.Post<UiState>(), BankStateCommunication
}

interface BankListCommunication : Communication.Mutable<List<BankUi>> {
    class Base() : Communication.Post<List<BankUi>>(), BankListCommunication
}


