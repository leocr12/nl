package com.example.nanoleaftest.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.nanoleaftest.components.NanoCard
import com.example.nanoleaftest.components.NanoForm
import com.example.nanoleaftest.viewmodel.NanoViewModel

/**
    • i, the total number of lightbulbs available

    • j, the number of lightbulb colours

    • k, the quantity of each lightbulb colour

    • m, the number of lightbulbs to randomly pick

    • n, the number of times to run the simulation
**/

@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: NanoViewModel) {

    val lightsAvailable by remember { viewModel.lightsAvailable }
    val coloursAmount by remember { viewModel.coloursAmount }
    val colours by remember { viewModel.colours }
    val coloursList by remember { viewModel.coloursList }
    val colourQuantity by remember { viewModel.colourQuantity }
    val colourPick by remember { viewModel.colourPick}
    val simulations by remember { viewModel.simulations }
    val result by remember { viewModel.result }

    Column(modifier = modifier) {
        NanoCard(lightbulbAmount = lightsAvailable)

        NanoForm(
            lightsAvailable = lightsAvailable,
            onLightsChange = viewModel::updateLightAvailableState,
            coloursAmount = coloursAmount,
            onColourChange = viewModel::updateColoursAmountState,
            colours = colours,
            onColoursChange =
            {
                viewModel.updateColoursState(it)

                val list = it.split(",").associateWith { colourQuantity.toInt() }
                viewModel.updateColourListState(list)
            },
            colourQuantity = colourQuantity,
            onQuantityChange = viewModel::updateColourQuantityState,
            colourPick = colourPick,
            onPickChange = viewModel::updateColourPickState,
            simulations = simulations,
            onSimulationChange = viewModel::updateSimulationsState
        )

        val context = LocalContext.current
        Button(onClick = {
            if (viewModel.isFormValid(lightsAvailable, coloursAmount, colourQuantity,
                    colourPick, simulations)) {
                val newResult = viewModel.runSimulation(
                    lightsAvailable.toInt(),
                    coloursAmount.toInt(),
                    coloursList,
                    colourQuantity.toInt(),
                    colourPick.toInt(),
                    simulations.toInt()
                )

                viewModel.updateResultState(newResult)
            } else {
                Toast.makeText(context, "Please fill in all the fields", Toast.LENGTH_LONG).show()
            }
        }) {
            Text(text = "Run Simulation")
        }

        Text(text = result)
    }
}