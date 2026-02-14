package com.ltdev.budgetsurpluscalculator.ui.theme.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.String
import kotlin.collections.List

data class EstimateSavingScreenState(
    val wishToSaveAmount: String = "",
    val timeWindowValue: String = "",
    val selectedTimeWindowUnit: String = "Day(s)",
    val timeWindowUnitsList: List<String> = listOf("Day(s)", "Week(s)", "Month(s)","Year(s)"),
    val dropBoxMenuExpanded: Boolean = false,
    val estimatedSavingPerTimeWindow : String = "0.0",
    val showDialog : Boolean = false
)

class EstimateSavingScreenViewModel : ViewModel() {

    private val _estimateSavingScreenState = MutableStateFlow(EstimateSavingScreenState())
    val estimateSavingScreenState: StateFlow<EstimateSavingScreenState> = _estimateSavingScreenState.asStateFlow()

    fun updateWishToSaveAValue(wishToSaveAmountValueInput: String){
        _estimateSavingScreenState.value = _estimateSavingScreenState.value.copy(
            wishToSaveAmount = wishToSaveAmountValueInput
        )
    }

    fun updateTimeWindowValue(timeWindowValueInput: String){
        _estimateSavingScreenState.value = _estimateSavingScreenState.value.copy(
            timeWindowValue = timeWindowValueInput
        )
    }

    fun calculateEstimatedSaving(){
        val doubleWishToSaveAmount = _estimateSavingScreenState.value.wishToSaveAmount.toDouble()
        val doubleTimeWindowValue = _estimateSavingScreenState.value.timeWindowValue.toInt()
        val doubleEstimatedSavingPerTimeWindow : Double = doubleWishToSaveAmount / doubleTimeWindowValue

        _estimateSavingScreenState.value = _estimateSavingScreenState.value.copy(
            estimatedSavingPerTimeWindow = String.format("%.2f", doubleEstimatedSavingPerTimeWindow)
        )

        _estimateSavingScreenState.value = _estimateSavingScreenState.value.copy(
            showDialog = true
        )

    }



    fun updateSelectedTimeWindowUnit(selectedTimeWIndowUnitInput: String){
        _estimateSavingScreenState.value = _estimateSavingScreenState.value.copy(
            selectedTimeWindowUnit = selectedTimeWIndowUnitInput
        )
    }

    fun dismissAlertDialog(){
        _estimateSavingScreenState.value = _estimateSavingScreenState.value.copy(
            showDialog = false
        )
    }

    fun resetAll(){
        _estimateSavingScreenState.value = _estimateSavingScreenState.value.copy(
            wishToSaveAmount = "",
            timeWindowValue = "",
            selectedTimeWindowUnit = "Day(s)",
            timeWindowUnitsList = listOf("Day(s)", "Week(s)", "Month(s)","Year(s)"),
            dropBoxMenuExpanded = false,
            estimatedSavingPerTimeWindow = "0.0",
            showDialog = false
        )
    }



}