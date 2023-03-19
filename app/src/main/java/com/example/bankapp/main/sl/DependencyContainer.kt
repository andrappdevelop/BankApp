package com.example.bankapp.main.sl

import androidx.lifecycle.ViewModel
import com.example.bankapp.bank.presentation.BankViewModel
import com.example.bankapp.bank.sl.BankModule

interface DependencyContainer {

    fun <T : ViewModel> module(clazz: Class<T>): Module<*>

    class Error : DependencyContainer {
        override fun <T : ViewModel> module(clazz: Class<T>): Module<*> {
            throw IllegalStateException("no module found for $clazz")
        }
    }

    class Base(
        private val core: Core,
        private val dependencyContainer: DependencyContainer = Error()
    ) : DependencyContainer {

        override fun <T : ViewModel> module(clazz: Class<T>): Module<*> =
            if (clazz == BankViewModel::class.java) {
                BankModule(core)
            } else {
                dependencyContainer.module(clazz)
            }
    }
}
