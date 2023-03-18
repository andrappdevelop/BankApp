package com.example.bankapp.bank.data

import com.example.bankapp.bank.domain.HandleError
import com.example.bankapp.bank.domain.NoInternetConnectionException
import com.example.bankapp.bank.domain.ServiceUnavailableException
import java.net.UnknownHostException

class HandleDomainError : HandleError<Exception> {
    override fun handle(e: Exception) = when (e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}