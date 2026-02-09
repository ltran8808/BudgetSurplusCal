package com.example.budgetsurpluscalculator.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TipCalScreen(viewModel: TipCalScreenViewModel = viewModel()){
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        val tipCalScreenState by viewModel.tipCalScreenUiState.collectAsStateWithLifecycle()
        val billSubtotal = tipCalScreenState.billSubTotal
        val onBillSubtotalValueChanged = viewModel :: updateBillSubtotal
        val tipPercent = tipCalScreenState.tipPercent
        val onTipPercentValueChanged = viewModel::updateTipPercent


//        TextField(
//            value = billSubtotal,
//            onValueChange = {viewModel.updateBillSubtotal(it)},
//            label = {Text(
//                "Please enter your bill Subtotal.",
//                fontSize = 8.sp
//            )},
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            singleLine = true
//        )

        InputField(billSubtotal, onValueChanged = onBillSubtotalValueChanged, "Please enter your bill Subtotal." )
        Spacer(modifier = Modifier
            .padding(8.dp))
        InputField(tipPercent,onTipPercentValueChanged,"Please enter how much you'd like to tip in percentage.")
        Spacer(modifier = Modifier
            .padding(8.dp))
        Button(onClick = {
            viewModel.calculateTip()
            viewModel.calculateTotalWithTip()
        }) {
            Text("OK")
        }

        Button(onClick = {
            viewModel.resetTipCal()
        }){
            Text("Reset")
        }

        Text(
            text = "Tip Amount: ${tipCalScreenState.tipAmount}"
        )

        Text(
            text = "Total: ${tipCalScreenState.totalWithTip}"
        )
    }
}

@Composable
fun InputField(
    textFieldValue: String,
    onValueChanged:(String)-> Unit,
    hintText: String,
){
    TextField(
        value = textFieldValue,
        onValueChange = {onValueChanged(it)},
        label = {Text(
            hintText,
            fontSize = 8.sp
        )},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}