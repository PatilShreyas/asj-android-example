package com.asj.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asj.example.ui.theme.AppTheme

@Composable
fun UsernameSearch(onSearch: (String) -> Unit) {
    var username by remember { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { newValue -> username = newValue },
            placeholder = {
                if (username.isBlank()) {
                    Text("Username")
                }
            }
        )
        Button(onClick = { onSearch(username) }) {
            Text("Search")
        }
    }
}

@Preview
@Composable
fun PreviewUsernameSearch() {
    AppTheme {
        UsernameSearch({})
    }
}