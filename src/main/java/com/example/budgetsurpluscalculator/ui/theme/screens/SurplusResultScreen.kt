package com.example.budgetsurpluscalculator.ui.theme.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SurplusResultScreen(
    viewModel: BudgetSurplusCalViewModel = viewModel(),
    onResetAllButtonClicked: () -> Unit
){

    val budgetSurplusCalUiState = viewModel.budgetSurplusCalUiState.collectAsStateWithLifecycle()
    val monthlySurplus = budgetSurplusCalUiState.value.monthlySurplus
    val savingGoalResult = budgetSurplusCalUiState.value.savingGoalResult.toDouble()
    val positiveSavingGoalResult = savingGoalResult * -1
    val totalMonthlyExpense = budgetSurplusCalUiState.value.monthlyTotalExpense

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SurplusOutlinedCard("Your monthly surplus is: ", monthlySurplus)

        when {
            savingGoalResult > 0 -> SurplusOutlinedCard("Congratulation! You have more than enough for your saving goal, and the extra amount is: ", savingGoalResult.toString())
            savingGoalResult < 0 -> SurplusOutlinedCard("I am sorry! You don't have enough to meet your saving goal. The amount you need to meet the saving goal is: ", positiveSavingGoalResult.toString())
            else -> SurplusOutlinedCard("Good job! Your surplus money is the exact amount you need for your saving goal.")

        }

        SurplusOutlinedCard("Your monthly total expense is: ",totalMonthlyExpense )

        Button (onClick = {
            viewModel.resetAll()
            onResetAllButtonClicked()
        }){
            Text("Reset All")
        }

    }
}

@Composable
fun SurplusOutlinedCard(
    message: String,
    toBeDisplayedValue: String = "0.0"
){
    OutlinedCard(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surface,
    ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(
            text = message,
            modifier = Modifier
                .padding(16.dp),

        )

        Text(
            text = toBeDisplayedValue,
            fontSize = 56.sp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.End

        )


    }

    Spacer(modifier = Modifier.padding(16.dp))
}

