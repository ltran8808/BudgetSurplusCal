package com.example.budgetsurpluscalculator.ui.theme.screens

import android.R.attr.padding
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.log
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun ExpenseInputScreen(
    viewModel: BudgetSurplusCalViewModel = viewModel(),
    onSubmitButtonClicked: () -> Unit,
) {



    val expenseInputList by viewModel.expenseList.collectAsStateWithLifecycle()


    val TAG = "ExpenseInputScreen"
    Log.d(TAG, "ExpenseInputScreen is initialized")



        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){



                items(expenseInputList, key = {it.id}) { expenseInput ->

                    ExpenseInputLayout(
                        expenseInput = expenseInput,
                        onExpenseInputChanged = {viewModel.updateExpenseList(expenseInput.id, it)}
                    )

                }
            item{
                Button(onClick =onSubmitButtonClicked
                ) {
                    Text("Submit")
                }
            }


        }



    }




@Composable
fun ExpenseInputLayout(
    expenseInput: ExpenseListItem,
    onExpenseInputChanged: (String) -> Unit
){
    TextField(
        value = expenseInput.expenseInput,
        onValueChange = onExpenseInputChanged,
        label = {Text("Bill Amount",
            fontSize = 8.sp)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )

    Spacer(modifier = Modifier.padding(8.dp))

}

@Preview
@Composable
fun ExpenseInputPreview(){

}