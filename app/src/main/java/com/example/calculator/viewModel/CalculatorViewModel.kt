package com.example.calculator.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculator.utils.CalculatorAction
import com.example.calculator.utils.CalculatorOperation
import com.example.calculator.utils.CalculatorState

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(
        CalculatorState()
    )
        private set

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Operation -> enterOperation(action.operator)
        }
    }

    private fun enterOperation(operator: CalculatorOperation) {
        if(state.number1.isNotBlank()) {
            state = state.copy(operator = operator)
        }
    }

    private fun enterDecimal() {
        //number 1
        if(state.operator == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
            state.copy(
                number1 = state.number1 + "."
            )
            return
        }
        //number 2
        else if(!state.number2.contains(".") && state.number2.isNotBlank()) {
            state.copy(
                number2 = state.number2 + "."
            )
        }
    }

    private fun performCalculation() {
        val num1 = state.number1.toDoubleOrNull()
        val num2 = state.number2.toDoubleOrNull()
        if(num1 != null && num2 != null) {
            val result = when(state.operator) {
                CalculatorOperation.Addition -> num1 + num2
                CalculatorOperation.Subtraction -> num1 - num2
                CalculatorOperation.Multiply -> num1 * num2
                CalculatorOperation.Divide -> num1 / num2
                null -> return
            }
            state = state.copy(
                number1 = result.toString(),
                number2 = "",
                operator = null
            )
        }
    }

    private fun performDeletion() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operator != null -> state = state.copy(
                operator = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun enterNumber(number: Int) {
        //number1
        if(state.operator == null) {
            if(state.number1.length >= MAX_DIGIT) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if(state.number2.length >= MAX_DIGIT) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }
    companion object {
        private const val MAX_DIGIT = 8
    }
}