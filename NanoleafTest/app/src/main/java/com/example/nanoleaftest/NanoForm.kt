package com.example.nanoleaftest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NanoForm(
    modifier: Modifier = Modifier,
    lightsAvailable: String,
    onLightsChange: (String) -> Unit,
    coloursAmount: String,
    onColourChange: (String) -> Unit,
    colours: String,
    onColoursChange: (String) -> Unit,
    colourQuantity: String,
    onQuantityChange: (String) -> Unit,
    colourPick: String,
    onPickChange: (String) -> Unit,
    simulations: String,
    onSimulationChange: (String) -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        //Lights Available - i
        TextField(
            value = lightsAvailable,
            onValueChange = onLightsChange,
            label = {
                Text(text = "Lightbulbs available")
            }
        )

        //Number of Lightbulb colours - j
        TextField(
            value = coloursAmount,
            onValueChange = onColourChange,
            label = {
                Text(text = "How many colours of lightbulbs ?")
            }
        )

        //Quantity of each colour - k
        TextField(
            value = colourQuantity,
            onValueChange = onQuantityChange,
            label = {
                Text(text = "How many of each do you have ? Must be an equal amount of each")
            }
        )

        if (colourQuantity.toInt() > 0) {
            TextField(
                value = colours,
                onValueChange = onColoursChange,
                label = {
                    Text(text = "Which colours do you have ? Separate with a comma")
                }
            )
        }

        //Number of lightbulbs to pick - m
        TextField(
            value = colourPick, 
            onValueChange = onPickChange,
            label = {
                Text(text = "How many lightbulbs to pick ?")
            }
        )

        //Number of simulations - n
        TextField(
            value = simulations, 
            onValueChange = onSimulationChange,
            label = {
                Text(text = "How many simulations to run ?")
            }
        )
    }
}