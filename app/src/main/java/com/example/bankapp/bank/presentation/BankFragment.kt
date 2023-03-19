package com.example.bankapp.bank.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.R
import com.example.bankapp.main.sl.ProvideViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class BankFragment : Fragment() {

    private lateinit var viewModel: BankViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).provideViewModel(
            BankViewModel::class.java,
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frament_bank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = view.findViewById<FrameLayout>(R.id.progressLayout)
        val searchButton = view.findViewById<Button>(R.id.searchButton)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val inputLayout = view.findViewById<TextInputLayout>(R.id.textInputLayout)
        val inputEditText = view.findViewById<TextInputEditText>(R.id.inputEditText)
        val adapter = BankAdapter(object : ClickListener {
            override fun click(item: BankUi) {
                inputEditText.setText(item.ui())
            }
        })

        recyclerView.adapter = adapter

        inputEditText.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                super.afterTextChanged(p0)
                viewModel.clearError()
            }
        })

        searchButton.setOnClickListener {
            viewModel.fetch(inputEditText.text.toString())
        }

        viewModel.observeState(this) {
            it.apply(inputLayout, inputEditText)
        }

        viewModel.observeList(this) {
            adapter.map(it)
        }
        viewModel.observeProgress(this) {
            progressBar.visibility = it
        }

        viewModel.init(savedInstanceState == null)
    }
}

abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

    override fun afterTextChanged(p0: Editable?) = Unit
}