package com.example.bankapp.bank.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BankDao {

    @Query("SELECT * FROM bank_table ORDER BY date DESC")
    fun allBins(): List<BankCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(number: BankCache)

    @Query("SELECT * FROM bank_table WHERE number = :number")
    fun fetch(number: String): BankCache?
}