package com.example.budgetsurpluscalculator.ui.theme.screens

import android.R.attr.padding
import android.util.Log
import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.log

@Composable
fun ExpenseInputScreen(
    modifier: Modifier = Modifier,
    viewModel: BudgetSurplusCalViewModel = viewModel()
){

    val expenseInputUiState by viewModel.budgetSurplusCalUiState.collectAsStateWithLifecycle()

    val TAG = "ExpenseInputScreen"
    Log.d(TAG, expenseInputUiState.monthlyNumberOfBills)

    Column(
        modifier = modifier
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val numberOfBills = expenseInputUiState.monthlyNumberOfBills.toInt()
        var index = 0

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
//            Log.d(TAG, numberOfBills.toString())

            while (index < numberOfBills){
                ExpenseInputLayout(
                    expenseInput = expenseInputUiState.expenseInputList[index].toString(),
                    onExpenseInputChanged = viewModel.
                )
                index++
            }


        }

        Button(onClick = {}) {
            Text("Submit")
        }
    }

}

@Composable
fun ExpenseInputLayout(
    expenseInput: String,
    onExpenseInputChanged: () -> Unit
){
    TextField(
        value = expenseInput,
        onValueChange = {onExpenseInputChanged},
        label = {Text("Bill Amount",
            fontSize = 8.sp)}
    )

    Spacer(modifier = Modifier.padding(8.dp))

}

@Preview
@Composable
fun ExpenseInputPreview(){
//    ExpenseInputScreen(
//    )
}