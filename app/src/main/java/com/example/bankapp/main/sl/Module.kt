package com.example.bankapp.main.sl

import androidx.lifecycle.ViewModel

interface Module<T : ViewModel> {

    fun viewModel(): T
}