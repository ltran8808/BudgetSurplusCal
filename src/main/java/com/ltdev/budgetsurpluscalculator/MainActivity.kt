package com.ltdev.budgetsurpluscalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ltdev.budgetsurpluscalculator.ui.theme.BudgetSurplusCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BudgetSurplusCalculatorTheme {
                BudgetSurplusCalculatorApp()
            }
        }
    }
}





