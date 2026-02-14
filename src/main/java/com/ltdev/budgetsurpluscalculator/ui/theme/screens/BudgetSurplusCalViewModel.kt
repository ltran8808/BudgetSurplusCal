package com.ltdev.budgetsurpluscalculator.ui.theme.screens

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.String

data class BudgetSurplusCalUiState(
    val monthlyIncome: String = "",
    val savingGoal: String = "",
    val monthlyNumberOfBills: String = "",
    val monthlySurplus: String = "",
    val savingGoalResult: String = "",
    val monthlyTotalExpense: String = ""
)

data class ExpenseListItem(
    val id: Int = 0,
    val expenseInput: String = ""
)


class BudgetSurplusCalViewModel : ViewModel(){

    private val TAG = "BudgetSurplusCalViewModel"


    private val _budgetSurplusCalUiState = MutableStateFlow(BudgetSurplusCalUiState())
    val budgetSurplusCalUiState: StateFlow<BudgetSurplusCalUiState> = _budgetSurplusCalUiState.asStateFlow()



    private val _expenseList = MutableStateFlow(emptyList<ExpenseListItem>())
    val expenseList: StateFlow<List<ExpenseListItem>> = _expenseList.asStateFlow()



    var x = 0

    fun updateMonthlyIncome(input:String){

        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(monthlyIncome = input)
    }

    fun updateSavingGoal(input:String) {

        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(savingGoal = input )
    }

    fun updateMonthlyNumberOfBills(input:String){


        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(
            monthlyNumberOfBills = input
        )

        Log.d(TAG,"Monthly number of bills is: " + budgetSurplusCalUiState.value.monthlyNumberOfBills)
    }

    fun createExpenseList(){
        while (x < _budgetSurplusCalUiState.value.monthlyNumberOfBills.toInt()){

            val expenseListItem : MutableList<ExpenseListItem> = mutableStateListOf(ExpenseListItem(x,""))
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

    }


    fun resetAll(){
        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(
            monthlyIncome = "",
            savingGoal = "",
            monthlyNumberOfBills = "",
            monthlySurplus = "",
            savingGoalResult = "",
            monthlyTotalExpense = ""
        )
    }

    /*When the below function is called,ExpenseInputScreen composable function must be called and
    hosted in NavHost. Then, an list must be created in order to keep the expenses that users are going to enter
    in the ExpenseInputScreen.
         */


    fun calculateMonthlySurplus(){
        val monthlyExpenseSum = _expenseList.value.map{it.expenseInput.toDouble()}

        Log.d(TAG,"Total monthly expense is: ${monthlyExpenseSum.sumOf{it}}")

        val doubleMonthlySurplus = _budgetSurplusCalUiState.value.monthlyIncome.toDouble() - monthlyExpenseSum.sumOf{it}

        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(monthlySurplus = ("%.2f".format(doubleMonthlySurplus)))

        Log.d(TAG, "Monthly Surplus is: ${_budgetSurplusCalUiState.value.monthlySurplus}")

        val doubleSavingGoalResult = doubleMonthlySurplus - _budgetSurplusCalUiState.value.savingGoal.toDouble()

        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(savingGoalResult = "%.2f".format(doubleSavingGoalResult))

        _budgetSurplusCalUiState.value = _budgetSurplusCalUiState.value.copy(monthlyTotalExpense = "%.2f".format(monthlyExpenseSum.sumOf { it }))

    }

}