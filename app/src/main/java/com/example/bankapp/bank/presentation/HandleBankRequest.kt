package com.example.bankapp.bank.presentation

import android.view.View
import androidx.lifecycle.viewModelScope
import com.example.bankapp.bank.domain.BankResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface HandleBankRequest {

    fun handle(
        coroutineScope: CoroutineScope,
        block: suspend () -> BankResult
    )

    class Base(
        private val dispatchers: DispatchersList,
        private val communications: BankCommunication,
        private val bankResultMapper: BankResult.Mapper<Unit>
    ) : HandleBankRequest {
        override fun handle(coroutineScope: CoroutineScope, block: suspend () -> BankResult) {
            communications.showProgress(View.VISIBLE)
            coroutineScope.launch(dispatchers.io()) {
                val result = block.invoke()
                communications.showProgress(View.GONE)
                result.map(bankResultMapper)
            }
        }
    }
}