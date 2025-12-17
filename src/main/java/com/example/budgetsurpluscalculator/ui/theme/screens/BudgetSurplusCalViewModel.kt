package com.example.budgetsurpluscalculator.ui.theme.screens

import android.util.Log
import androidx.collection.MutableDoubleList
import androidx.collection.mutableDoubleListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BudgetSurplusCalUiState(
    val monthlyIncome: String = "0.0",
    val savingGoal: String = "0.0",
    val monthlyNumberOfBills: String = "0",
    val expenseInputList : MutableList<Double> = mutableListOf(),
    val expenseInput : String = "0.0"
)


class BudgetSurplusCalViewModel : ViewModel(){

    private val TAG = "BudgetSurplusCalViewModel"


    private val _budgetSurplusCalUiState = MutableStateFlow(BudgetSurplusCalUiState())
    val budgetSurplusCalUiState: StateFlow<BudgetSurplusCalUiState> = _budgetSurplusCalUiState



    var index : Int = 0



    fun updateMonthlyIncome(input:String){
//        monthlyIncome = input
        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(monthlyIncome = input)
    }

    fun updateSavingGoal(input:String) {
//        monthlySavingGoal = input
        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(savingGoal = input )
    }

    fun updateMonthlyNumberOfBills(input:String){
//        monthlyNumberOfBills = input
//        _budgetSurplusCalUiState.value.monthlyNumberOfBills = input

        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(
            monthlyNumberOfBills = input
        )

        Log.d(TAG,"Monthly number of bills is: " + budgetSurplusCalUiState.value.monthlyNumberOfBills)
    }




    fun createExpenseList(){
        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(
            expenseInputList = MutableList(_budgetSurplusCalUiState.value.monthlyNumberOfBills.toInt()){0.0}
        )
        Log.d(TAG, "Expense Input List's size is: " + _budgetSurplusCalUiState.value.expenseInputList.size)
    }

    fun updateExpenseInput(input : String) {
        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(

        )
    }


    fun reset(){

    }

    /*When the below function is called,ExpenseInputScreen composable function must be called and
    hosted in NavHost. Then, an list must be created in order to keep the expenses that users are going to enter
    in the ExpenseInputScreen.
         */
    fun submit(){
//        createExpenseInputList()
    }

}