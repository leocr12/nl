package com.example.nanoleaftest.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.math.RoundingMode
import java.util.*

class NanoViewModel: ViewModel() {

    var lightsAvailable = mutableStateOf("")
    var coloursAmount = mutableStateOf("")
    var colours = mutableStateOf("")
    var coloursList = mutableStateOf(mapOf<String, Int>())
    var colourQuantity = mutableStateOf("")
    var colourPick = mutableStateOf("")
    var simulations = mutableStateOf("")
    var result = mutableStateOf("")

    fun updateLightAvailableState(newValue: String) {
        lightsAvailable.value = newValue
    }

    fun updateColoursAmountState(newValue: String) {
        coloursAmount.value = newValue
    }

    fun updateColoursState(newValue: String) {
        colours.value = newValue
    }

    fun updateColourListState(newValue: Map<String, Int>) {
        coloursList.value = newValue
    }

    fun updateColourQuantityState(newValue: String) {
        colourQuantity.value = newValue
    }

    fun updateColourPickState(newValue: String) {
        colourPick.value = newValue
    }

    fun updateSimulationsState(newValue: String) {
        simulations.value = newValue
    }

    fun updateResultState(newValue: String) {
        result.value = newValue
    }

    fun isFormValid(lightsAvailable: String, coloursAmount: String, colourQuantity: String,
                            colourPick: String, simulations: String): Boolean {
        if (lightsAvailable.isBlank() || coloursAmount.isBlank() || colourQuantity.isBlank()
            || colourPick.isBlank() || simulations.isBlank()) {
            return false
        }
        return true
    }

    fun runSimulation(i: Int, j: Int, colourList: Map<String, Int>, k: Int, m: Int, n: Int): String {
        var sum = 0.00

        if (i != j * k) return "Number of colours not equal to number of lightbulbs"

        if (j != colourList.size) return "Colours provided do not match number of colours"

        for (z in 0 until n) {
            val uniqueColorsCount = getUniqueColours(colourList, m)
            sum += uniqueColorsCount
        }

        return (sum/n).toBigDecimal().setScale(2, RoundingMode.FLOOR).toString()
    }

    private fun getUniqueColours(colourList: Map<String, Int>, m: Int): Int {
        val uniqueColours = mutableSetOf<String>()
        val uniqueMap = colourList.toMutableMap()
        val random = Random()

        for (i in 0 until m) {
            val colour = uniqueMap.entries.elementAt(random.nextInt(uniqueMap.size)).key
            uniqueMap[colour] = (uniqueMap[colour]?.minus(1)) ?: 0
            if (uniqueMap[colour] == 0) {
                uniqueMap.remove(colour)
            }
            uniqueColours.add(colour)
        }
        return uniqueColours.size
    }
}