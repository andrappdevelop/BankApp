package com.example.bankapp.main.sl

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.bankapp.BuildConfig

class BankApp : Application(), ProvideViewModel {

    private lateinit var viewModelsFactory: ViewModelsFactory

    override fun onCreate() {
        super.onCreate()

        val provideInstances = ProvideInstances.Release(this)

        viewModelsFactory =
            ViewModelsFactory(
                DependencyContainer.Base(Core.Base(this, provideInstances))
            )
    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, viewModelsFactory)[clazz]
}