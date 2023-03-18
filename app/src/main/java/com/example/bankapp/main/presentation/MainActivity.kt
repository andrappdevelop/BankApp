package com.example.bankapp.main.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bankapp.R
import com.example.bankapp.bank.presentation.BankFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BankFragment())
                .commit()
    }
}