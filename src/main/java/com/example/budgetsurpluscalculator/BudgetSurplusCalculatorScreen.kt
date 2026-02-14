package com.example.budgetsurpluscalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.budgetsurpluscalculator.ui.theme.screens.BudgetSurplusCalViewModel
import com.example.budgetsurpluscalculator.ui.theme.screens.EstimateSavingScreen
import com.example.budgetsurpluscalculator.ui.theme.screens.EstimateSavingScreenViewModel
import com.example.budgetsurpluscalculator.ui.theme.screens.ExpenseInputScreen
import com.example.budgetsurpluscalculator.ui.theme.screens.InputBasicBudgetInfoScreen
import com.example.budgetsurpluscalculator.ui.theme.screens.SurplusResultScreen
import com.example.budgetsurpluscalculator.ui.theme.screens.TipCalScreen
import com.example.budgetsurpluscalculator.ui.theme.screens.TipCalScreenViewModel

/*
THIS IS WHERE THE SCREENS ARE DISPLAYED WITH NAVIGATION (HOST, CONTROLLER AND ROUTE)
 */

enum class BudgetSurplusCalculatorScreens(){
    Start,
    ExpenseInput,
    SurplusResultScreen,
    EstimateSavingCalculatorScreen,
    TipCalScreen
}

enum class Destination(
    val route : String,
    val label : String,
    val icon : ImageVector,
    val contentDescription: String
){
    TIPCAL(BudgetSurplusCalculatorScreens.TipCalScreen.name, "Tip Calculator", Icons.Default.Star,"Tip Calculator"),
    ESTSAVCAL(BudgetSurplusCalculatorScreens.EstimateSavingCalculatorScreen.name, "Estimate Saving Calculator", Icons.Default.Build, "Estimate Saving Calculator"),
    SURPLUSCAL(BudgetSurplusCalculatorScreens.Start.name, "Surplus Calculator", Icons.Default.Favorite, "Surplus Calculator")
}




@Composable
fun BudgetSurplusCalculatorApp(
    viewModel: BudgetSurplusCalViewModel = viewModel(),

){

    val navController = rememberNavController()
    val startDestination = Destination.TIPCAL
    var selectedDestination by rememberSaveable{mutableStateOf(startDestination.ordinal)}


    Scaffold(
        bottomBar = {
            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets
            ){
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = {Text(destination.label)}
                    )
                }
            }
        }
    ) { innerPadding ->

      NavHost(
            navController = navController,
            startDestination= BudgetSurplusCalculatorScreens.TipCalScreen.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            composable(route = BudgetSurplusCalculatorScreens.Start.name){
                InputBasicBudgetInfoScreen(
                    viewModel = viewModel,
                    onMonthlyNumberOfBillsChanged = {viewModel.updateMonthlyNumberOfBills(it)},
                    onSubmitButtonClicked = {
                        navController.navigate(BudgetSurplusCalculatorScreens.ExpenseInput.name)
                        viewModel.createExpenseList()
                                            },
                    onResetAllButtonClick = {
                        navController.navigate(BudgetSurplusCalculatorScreens.Start.name){
                            popUpTo(navController.graph.startDestinationId){
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(route = BudgetSurplusCalculatorScreens.ExpenseInput.name){
                ExpenseInputScreen(
                    viewModel = viewModel,
                    onSubmitButtonClicked = {
                        navController.navigate(BudgetSurplusCalculatorScreens.SurplusResultScreen.name)
                        viewModel.calculateMonthlySurplus()
                    }
              )
            }

            composable(route = BudgetSurplusCalculatorScreens.SurplusResultScreen.name) {
                SurplusResultScreen(
                    viewModel = viewModel,
                    onResetAllButtonClicked = {
                        navController.navigate(BudgetSurplusCalculatorScreens.Start.name) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    }

                    )
            }

          composable(route = BudgetSurplusCalculatorScreens.TipCalScreen.name){
              TipCalScreen(
              )
          }

          composable(route = BudgetSurplusCalculatorScreens.EstimateSavingCalculatorScreen.name){
              EstimateSavingScreen()
          }
        }

    }

}





