package com.example.it_arch_client.ui.main

import com.example.it_arch_client.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        val wordsText = buildAnnotatedString {
            withStyle(SpanStyle(fontSize = 24.sp)){
                append(words)
            }
            append(stringResource(id = R.string.counts_unit))
        }

        Text(
            text = stringResource(id = R.string.header_text),
            fontSize = 24.sp
        )
        Button(
            onClick = {
                countWords(text)
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.button_text))
        }
        Text(text = wordsText)
        OutlinedTextField(value = text, onValueChange = {text = it})
    }
}