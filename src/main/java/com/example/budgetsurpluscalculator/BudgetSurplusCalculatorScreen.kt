package com.example.budgetsurpluscalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.budgetsurpluscalculator.ui.theme.screens.BudgetSurplusCalViewModel
import com.example.budgetsurpluscalculator.ui.theme.screens.ExpenseInputScreen
import com.example.budgetsurpluscalculator.ui.theme.screens.InputBasicBudgetInfoScreen

/*
THIS IS WHERE THE SCREENS ARE DISPLAYED WITH NAVIGATION (HOST, CONTROLLER AND ROUTE)
 */

enum class BudgetSurplusCalculatorScreens(){
    Start,
    ExpenseInput
}

//@Composable
//fun BudgetSurplusCalculatorAppBar(
//    currentScreen: BudgetSurplusCalculatorScreens,
//    canNavigateBack: Boolean,
//    navigateUp: () -> Unit,
//    modifier: Modifier = Modifier
//){
//    TopAppBar(
//        title = {
//            Text(stringResource(currentScreen))
//        }
//    )
//}


@Composable
fun BudgetSurplusCalculatorApp(
    viewModel: BudgetSurplusCalViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the name of the current screen
    val currentScreen = BudgetSurplusCalculatorScreens.valueOf(
        backStackEntry?.destination?.route ?: BudgetSurplusCalculatorScreens.Start.name
    )

    Scaffold(
        topBar = {
//            BudgetSurplusCalculatorAppBar(
//                currentScreen = currentScreen,
//                canNavigateBack = navController.previousBackStackEntry != null,
//                navigateUp = {navController.navigateUp()}
//            )

        }
    ) { innerPadding ->


      NavHost(
            navController = navController,
            startDestination= BudgetSurplusCalculatorScreens.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ){
            composable(route = BudgetSurplusCalculatorScreens.Start.name){
                InputBasicBudgetInfoScreen()
            }

          composable(route = BudgetSurplusCalculatorScreens.ExpenseInput.name){
              ExpenseInputScreen()
          }
        }

    }

}

@Preview
@Composable
fun BudgetSurplusCalculatorScreen(){
    BudgetSurplusCalculatorApp()
}




