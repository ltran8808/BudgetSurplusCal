package com.example.budgetsurpluscalculator.ui.theme.screens

import android.util.Log
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
    val savingGoal: String = "0.0",
    val monthlyNumberOfBills: String = "0"
)


class BudgetSurplusCalViewModel : ViewModel(){

    private val TAG = "BudgetSurplusCalViewModel"


    private val _budgetSurplusCalUiState = MutableStateFlow(BudgetSurplusCalUiState())
    val budgetSurplusCalUiState: StateFlow<BudgetSurplusCalUiState> = _budgetSurplusCalUiState


//    val monthlyIncome by mutableStateOf(_budgetSurplusCalUiState.value.monthlyIncome)
//        private set
//
//    val monthlySavingGoal by mutableStateOf(_budgetSurplusCalUiState.value.monthlySavingGoal)
//        private set
//
//    val monthlyNumberOfBills by mutableStateOf(_budgetSurplusCalUiState.value.monthlyNumberOfBills)
//        private set

//    val _monthlyNumberOfBills : Int = monthlyNumberOfBills.toInt()

//    val expenseInputList: MutableList<String> = mutableListOf()
//        private set

    var index: Int = 0

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

        Log.d(TAG,budgetSurplusCalUiState.value.monthlyNumberOfBills)
    }



//    fun createExpenseInputList(){
//        while (index < budgetSurplusCalUiState.value.monthlyNumberOfBills.toInt()){
//            expenseInputList.add("0.0")
//            index++
//        }
//    }
//
//    fun updateExpenseInput(index:Int,input:String){
//        expenseInputList[index] = input
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

}