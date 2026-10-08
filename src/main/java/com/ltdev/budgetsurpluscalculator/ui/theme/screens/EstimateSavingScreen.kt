package com.ltdev.budgetsurpluscalculator.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EstimateSavingScreen(
    viewModel: EstimateSavingScreenViewModel = viewModel(),
){
    val estimateSavingScreenStates = viewModel.estimateSavingScreenState.collectAsStateWithLifecycle()

    val wishToSaveAmount = estimateSavingScreenStates.value.wishToSaveAmount
    val timeWindowValue = estimateSavingScreenStates.value.timeWindowValue
    val timeWindowUnitsList = estimateSavingScreenStates.value.timeWindowUnitsList
    val showDialog = estimateSavingScreenStates.value.showDialog
    val savingAmount = estimateSavingScreenStates.value.estimatedSavingPerTimeWindow
    val timeWindowUnit = estimateSavingScreenStates.value.selectedTimeWindowUnit
    val context = LocalContext.current


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
        TextField(
            value = wishToSaveAmount,
            onValueChange = {viewModel.updateWishToSaveAValue(it)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            label = {Text(
                "Please enter your wish-to-save amount.",
                fontSize = 8.sp
            )}
        )

        Spacer(
            modifier = Modifier
                .padding(8.dp)
        )

        TimeWindowElement(
            timeWindowValue = timeWindowValue,
            onTimeWindowValueChanged = { timeWindowValueInput: String ->
                viewModel.updateTimeWindowValue(timeWindowValueInput)
            },
            timeWindowUnitsList = timeWindowUnitsList,
            updateTimeWindowUnit = {viewModel.updateSelectedTimeWindowUnit(it)}
        )

        Spacer(
            modifier = Modifier
                .padding(8.dp)
            )

        Button(onClick ={
            if(wishToSaveAmount.isBlank() || timeWindowValue.isBlank()){
            Toast.makeText(context, "Please enter needed information in the above fields!", Toast.LENGTH_LONG).show()
            } else { viewModel.calculateEstimatedSaving() }}
        ){
            Text("Ok")
        }

        Spacer(
            modifier = Modifier
                .padding(4.dp)
            )

        Button(onClick = {viewModel.resetAll()}) {
            Text("Reset")
        }

        if (showDialog){
            AlertDialog(
                onDismissRequest = {
                    viewModel.dismissAlertDialog()
                },
                confirmButton = {
                    TextButton(onClick = {viewModel.dismissAlertDialog()}
                    ){
                        Text("Cool")
                    }
                },
                text = {Text ("You need to put away $savingAmount every $timeWindowUnit to obtain your saving goal.")}
                )
            }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeWindowElement(
    timeWindowValue : String,
    onTimeWindowValueChanged : (String) -> Unit,
    timeWindowUnitsList: List<String>,
    updateTimeWindowUnit : (String) -> Unit
){
    val options = timeWindowUnitsList
    var expanded by remember{ mutableStateOf(false) }
    var selectedOptionText by remember{mutableStateOf(options[0])}

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        TextField(
            value = timeWindowValue,
            onValueChange = {timeWindowValueInput ->
                onTimeWindowValueChanged(timeWindowValueInput)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier
                .width(150.dp),
            label = {Text(
                text = "Time Window",
                fontSize = 8.sp
            )}

        )

        Spacer(
            modifier = Modifier
                .padding(8.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {expanded = !expanded}

        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                singleLine = true
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = !expanded}
            ) {
                options.forEach { selectionOption->
                    DropdownMenuItem(
                        text = {Text(selectionOption)},
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                            updateTimeWindowUnit(selectionOption)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}



@Preview
@Composable
fun EstimateSavingScreenTheme(){
    EstimateSavingScreen()
}