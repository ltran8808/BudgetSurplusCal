package com.example.budgetsurpluscalculator.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InputBasicBudgetInfoScreen(
    onSubmitButtonClicked : () -> Unit,
    modifier: Modifier = Modifier,
    viewModel : BudgetSurplusCalViewModel = viewModel()
){

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
            modifier = Modifier
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(
                value = viewModel.monthlyIncome ,
                onValueChange = {viewModel.updateMonthlyIncome(it)},
                label = {Text(
                    "Please enter your monthly income.",
                    fontSize = 8.sp
                )},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            Spacer(modifier = Modifier
                .padding(16.dp))

            TextField(
                value = viewModel.monthlySavingGoal,
                onValueChange = {viewModel.updateMonthlySavingGoal(it)},
                label = {Text(
                    "Please enter your monthly saving goal.",
                    fontSize = 8.sp
                )},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
            )

            Spacer(modifier = Modifier
                .padding(16.dp))

            TextField(
                value = viewModel.monthlyNumberOfBills,
                onValueChange = {viewModel.updateMonthlyNumberOfBills(it)},
                label = {Text(
                    "Please enter your monthly number of bills.",
                    fontSize = 8.sp
                )},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Button(onClick = {viewModel.reset()}) {
                Text("Reset")
            }

            Spacer(modifier = Modifier
                .padding(16.dp))

            Button(onClick = onSubmitButtonClicked) {
                Text("Submit")
            }

        }
    }

}

@Preview
@Composable
fun InputBasicBudgetInfoPreview(){

        InputBasicBudgetInfoScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            onSubmitButtonClicked = {}
        )

}