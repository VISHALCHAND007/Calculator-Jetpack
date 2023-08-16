package com.example.calculator.utils

sealed class CalculatorOperation(val operator: String) {
    object Addition: CalculatorOperation("+")
    object Subtraction: CalculatorOperation("-")
    object Multiply: CalculatorOperation("x")
    object Divide: CalculatorOperation("/")
}
