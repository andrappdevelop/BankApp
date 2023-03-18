package com.example.bankapp.bank.data

import retrofit2.http.GET
import retrofit2.http.Path

interface BankService {

    @GET("{number}")
    suspend fun binInfo(@Path("number") number: String): BinCloud
}