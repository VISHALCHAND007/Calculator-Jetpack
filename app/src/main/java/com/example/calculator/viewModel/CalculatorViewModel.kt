package com.example.calculator.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculator.utils.CalculatorState

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(
        CalculatorState()
    )
    private set

}