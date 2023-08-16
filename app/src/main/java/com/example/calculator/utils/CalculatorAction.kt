package com.example.calculator.utils

sealed class CalculatorAction {
    data class Number(val number: Int): CalculatorAction()
    object Delete: CalculatorAction()
    object Clear: CalculatorAction()
    object Calculate: CalculatorAction()
    object Decimal: CalculatorAction()
    data class Operation(val operator: CalculatorOperation): CalculatorAction()
}
