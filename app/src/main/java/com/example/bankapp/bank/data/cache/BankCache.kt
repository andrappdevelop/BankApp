package com.example.bankapp.bank.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bank_table")
data class BankCache(
    @PrimaryKey @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "binInfo") val binInfo: String,
    @ColumnInfo(name = "date") val date: Long
)