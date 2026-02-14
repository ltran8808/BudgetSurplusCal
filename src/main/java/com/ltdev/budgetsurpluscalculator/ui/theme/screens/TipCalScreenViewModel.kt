package com.ltdev.budgetsurpluscalculator.ui.theme.screens

import android.text.TextUtils.isEmpty
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.String

data class TipCalScreenUiState(
    val billSubTotal: String = " ",
    val tipPercent: String = " ",
    val tipAmount: String = " ",
    val totalWithTip: String = " "
)

class TipCalScreenViewModel : ViewModel() {
    private val _tipCalScreenUiState = MutableStateFlow(TipCalScreenUiState())
    val tipCalScreenUiState: StateFlow<TipCalScreenUiState> = _tipCalScreenUiState.asStateFlow()

    fun updateBillSubtotal(billSubTotalInput: String){

        _tipCalScreenUiState.value = _tipCalScreenUiState.value.copy(
            billSubTotal = billSubTotalInput
        )

    }

    fun updateTipPercent(tipPercentInput: String){
        _tipCalScreenUiState.value = _tipCalScreenUiState.value.copy(
            tipPercent = tipPercentInput
        )
    }

    fun calculateTip(){
        val tipAmount = (_tipCalScreenUiState.value.billSubTotal.toDouble() * _tipCalScreenUiState.value.tipPercent.toDouble()) / 100

        _tipCalScreenUiState.value = _tipCalScreenUiState.value.copy(
            tipAmount = ("%.2f".format(tipAmount))
        )
    }

    fun calculateTotalWithTip(){

        val totalWithTip = _tipCalScreenUiState.value.tipAmount.toDouble() + _tipCalScreenUiState.value.billSubTotal.toDouble()

        _tipCalScreenUiState.value = _tipCalScreenUiState.value.copy(
            totalWithTip = ("%.2f".format(totalWithTip))
        )

    }

    fun resetTipCal(){
        _tipCalScreenUiState.value = _tipCalScreenUiState.value.copy(
            billSubTotal = " ",
            tipPercent = " ",
            tipAmount = " ",
            totalWithTip = " "
        )
    }
}