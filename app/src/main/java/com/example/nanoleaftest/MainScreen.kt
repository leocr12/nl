package com.example.nanoleaftest

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import java.math.RoundingMode
import java.util.*

/**
    • i, the total number of lightbulbs available

    • j, the number of lightbulb colours

    • k, the quantity of each lightbulb colour

    • m, the number of lightbulbs to randomly pick

    • n, the number of times to run the simulation
**/

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    var lightsAvailable by remember { mutableStateOf("") }
    var coloursAmount by remember { mutableStateOf("") }
    var colours by remember { mutableStateOf("") }
    var coloursList by remember { mutableStateOf(mapOf<String, Int>()) }
    var colourQuantity by remember { mutableStateOf("") }
    var colourPick by remember { mutableStateOf("") }
    var simulations by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        NanoCard(lightbulbAmount = "10")

        NanoForm(
            lightsAvailable = lightsAvailable,
            onLightsChange = { lightsAvailable = it },
            coloursAmount = coloursAmount,
            onColourChange = { coloursAmount = it },
            colours = colours,
            onColoursChange =
            {
                colours = it
                coloursList = it.split(",").associateWith { colourQuantity.toInt() }
            },
            colourQuantity = colourQuantity,
            onQuantityChange = { colourQuantity = it },
            colourPick = colourPick,
            onPickChange = { colourPick = it },
            simulations = simulations,
            onSimulationChange = { simulations = it }
        )

        Button(onClick = {
            result = runSimulation(
                lightsAvailable.toInt(),
                coloursAmount.toInt(),
                coloursList,
                colourQuantity.toInt(),
                colourPick.toInt(),
                simulations.toInt()
            )
        }) {
            Text(text = "Run Simulation")
        }

        Text(text = result)
    }
}

private fun runSimulation(i: Int, j: Int, colourList: Map<String, Int>, k: Int, m: Int, n: Int): String {
    var sum = 0L

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