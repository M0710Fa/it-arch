package com.example.it_arch_client.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    countWords:(String) -> Unit,
    words: String,
) {

    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Count Words in Sentence"
        )
        Button(
            onClick = {
                countWords(text)
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Count!!")
        }
        Text(text = "$words Words")
        OutlinedTextField(value = text, onValueChange = {text = it})
    }
}