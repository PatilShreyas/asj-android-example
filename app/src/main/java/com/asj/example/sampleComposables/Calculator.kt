package com.asj.example.sampleComposables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.lang.Exception

@Composable
fun Calculator() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }

    var result: Int? by remember { mutableStateOf(null) }
    var errorMessage: String? by remember { mutableStateOf(null) }
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = number1,
                onValueChange = { newValue ->
                    number1 = newValue
                },
                modifier = Modifier.width(100.dp)
            )
            TextField(
                value = number2,
                onValueChange = { newValue ->
                    number2 = newValue
                },
                modifier = Modifier.width(100.dp)
            )
        }
        Button(onClick = {
            errorMessage = null
            try {
                result = number1.toInt() + number2.toInt()
            } catch (e: Exception) {
                errorMessage = "Invalid Input"
            }
        }) {
            Text("+")
        }

        if (result != null) {
            Text("Result = $result")
        }

        if (errorMessage != null) {
            Text("Error occurred: $errorMessage", color = Color.Red)
        }
    }
}