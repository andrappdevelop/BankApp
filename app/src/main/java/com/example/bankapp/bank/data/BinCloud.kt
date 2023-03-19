package com.example.bankapp.bank.data

import com.google.gson.annotations.SerializedName

data class BinCloud(
    @SerializedName("number") val number: Number = Number(),
    @SerializedName("scheme") val scheme: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("brand") val brand: String = "",
    @SerializedName("prepaid") val prepaid: Boolean = false,
    @SerializedName("country") val country: Country = Country(),
    @SerializedName("bank") val bank: Bank = Bank()
)