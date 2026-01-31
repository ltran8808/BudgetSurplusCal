package com.example.budgetsurpluscalculator.ui.theme.screens

import android.util.Log
import android.widget.Toast
import androidx.collection.MutableDoubleList
import androidx.collection.mutableDoubleListOf
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.apply

data class BudgetSurplusCalUiState(
    val monthlyIncome: String = "0.0",
    val savingGoal: String = "0.0",
    val monthlyNumberOfBills: String = "0",
    val monthlySurplus: String = "0.0",
    val savingGoalResult: String = "0.0"
)

data class ExpenseListItem(
    val id: Int,
    val expenseInput: String
)
//
//data class ExpenseInputScreenState(
//    val items: List<ExpenseItemState> = emptyList()
//)

class BudgetSurplusCalViewModel : ViewModel(){

    private val TAG = "BudgetSurplusCalViewModel"


    private val _budgetSurplusCalUiState = MutableStateFlow(BudgetSurplusCalUiState())
    val budgetSurplusCalUiState: StateFlow<BudgetSurplusCalUiState> = _budgetSurplusCalUiState.asStateFlow()

    private val _expenseItemState = MutableStateFlow<List<String>>(emptyList())
    val expenseItemState: StateFlow<List<String>> = _expenseItemState.asStateFlow()

    private val _expenseList = MutableStateFlow(emptyList<ExpenseListItem>())
    val expenseList: StateFlow<List<ExpenseListItem>> = _expenseList.asStateFlow()

    var x = 0

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


        while (x < _budgetSurplusCalUiState.value.monthlyNumberOfBills.toInt()){
//            _expenseItemState.value = _expenseItemState.value + "2.0"
            val expenseListItem : MutableList<ExpenseListItem> = mutableStateListOf(ExpenseListItem(x,"0.0"))
            _expenseList.value = _expenseList.value + expenseListItem
            x++
        }

        Log.d(TAG, "Expense Input List's size is: " + _expenseList.value.size)
    }

    fun updateExpenseList( expenseItemId: Int, billBalance:String) {
        Log.d(TAG, "TextField "+ expenseItemId + " is being edited")


        _expenseList.update { currentList ->
            currentList.map{item ->
                if (item.id == expenseItemId){
                    item.copy(expenseInput = billBalance)
                } else {
                    item
                }
            }
        }

//        _expenseList.value = _expenseList.value.toMutableList().apply { this[expenseItemId] = billBalance }
        //        Log.d(TAG, "Expense Input List's size is: " + _budgetSurplusCalUiState.value.expenseInputList.size)
        }





//    fun updateExpenseInput(input: String){
//        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(
//            expenseInput = input
//        )
//    }


    fun reset(){

    }

    /*When the below function is called,ExpenseInputScreen composable function must be called and
    hosted in NavHost. Then, an list must be created in order to keep the expenses that users are going to enter
    in the ExpenseInputScreen.
         */
    fun submit(){
//        createExpenseInputList()
    }

    fun calculateMonthlySurplus(){
        val monthlyExpenseSum = _expenseList.value.map{it.expenseInput.toDouble()}

        Log.d(TAG,"Total monthly expense is: ${monthlyExpenseSum.sumOf{it}}")

        val doubleMonthlySurplus = _budgetSurplusCalUiState.value.monthlyIncome.toDouble() - monthlyExpenseSum.sumOf{it}

        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(monthlySurplus = doubleMonthlySurplus.toString() )

        Log.d(TAG, "Monthly Surplus is: ${_budgetSurplusCalUiState.value.monthlySurplus}")

        val doubleSavingGoalResult = doubleMonthlySurplus - _budgetSurplusCalUiState.value.savingGoal.toDouble()

        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(savingGoalResult = doubleSavingGoalResult.toString())

    }

}