package com.example.budgetsurpluscalculator.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class BudgetSurplusCalUiState(
    val monthlyIncome: String = "0.0",
    val monthlyNumberOfBills: String = "0",
    val monthlySavingGoal: String = "0.0"
)

class BudgetSurplusCalViewModel : ViewModel(){

    private var _budgetSurplusCalUiState = MutableStateFlow(BudgetSurplusCalUiState())
    val budgetSurplusCalUiState: StateFlow<BudgetSurplusCalUiState> = _budgetSurplusCalUiState.asStateFlow()

    var monthlyIncome by mutableStateOf("")
        private set

    var monthlyNumberOfBills by mutableStateOf("")
        private set

    var monthlySavingGoal by mutableStateOf("")
        private set

    fun updateMonthlyIncome(input:String){
        monthlyIncome = input
    }

    fun updateMonthlyNumberOfBills(input:String){
        monthlyNumberOfBills = input
    }

    fun updateMonthlySavingGoal(input:String) {
        monthlySavingGoal = input
    }


    fun reset(){
        monthlyIncome = ""
        monthlySavingGoal = ""
        monthlyNumberOfBills = ""
    }

    fun submit(){

    }

}