package com.example.bankapp.bank.data.cloud

import com.example.bankapp.bank.data.BinCloud
import retrofit2.http.GET
import retrofit2.http.Path

interface BankService {

    @GET("{number}")
    suspend fun binInfo(@Path("number") number: String): BinCloud
}