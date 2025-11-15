package com.example.budgetsurpluscalculator.ui.theme.screens

import androidx.collection.MutableDoubleList
import androidx.collection.mutableDoubleListOf
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

    var monthlyNumberOfBills by mutableStateOf("5")
        private set

    var monthlySavingGoal by mutableStateOf("")
        private set

    val _monthlyNumberOfBills : Double = monthlyNumberOfBills.toDouble()

    var expenseInputList: MutableList<String> = mutableListOf()
        private set

    var index: Int = 0

    fun updateMonthlyIncome(input:String){
        monthlyIncome = input
    }

    fun updateMonthlyNumberOfBills(input:String){
        monthlyNumberOfBills = input
    }

    fun updateMonthlySavingGoal(input:String) {
        monthlySavingGoal = input
    }

    fun createExpenseInputList(){
        while (index < _monthlyNumberOfBills){
            expenseInputList.add("0.0")
            index
        }
    }

    fun updateExpenseInput(index:Int,input:String){
        expenseInputList[index] = input
    }


    fun reset(){
        monthlyIncome = ""
        monthlySavingGoal = ""
        monthlyNumberOfBills = ""
    }

    /*When the below function is called,ExpenseInputScreen composable function must be called and
    hosted in NavHost. Then, an list must be created in order to keep the expenses that users are going to enter
    in the ExpenseInputScreen.
         */
    fun submit(){
        createExpenseInputList()
    }

}