package com.example.bankapp.bank.presentation

import com.example.bankapp.bank.domain.BankResult
import com.example.bankapp.bank.domain.BinItem

class BankResultMapper(
    private val communications: BankCommunication,
    private val mapper: BinItem.Mapper<BankUi>
) : BankResult.Mapper<Unit> {

    override fun map(list: List<BinItem>, errorMessage: String) = communications.showState(
        if (errorMessage.isEmpty()) {
            val bankList = list.map { it.map(mapper) }
            if (bankList.isNotEmpty())
                communications.showList(bankList)
            UiState.Success()
        } else
            UiState.Error(errorMessage)
    )
}