package com.example.bankapp.bank.data

import com.google.gson.annotations.SerializedName

data class Number(
    @SerializedName("length") val length: Int? = null,
    @SerializedName("luhn") val luhn: Boolean = false
)
