package com.example.calculator.utils

import com.example.calculator.utils.CalculatorOperation

data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val operator: CalculatorOperation? = null
)
