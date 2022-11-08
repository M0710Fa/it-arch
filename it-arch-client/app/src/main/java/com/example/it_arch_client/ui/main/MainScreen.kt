package com.example.it_arch_client.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.it_arch_client.ui.theme.ItarchclientTheme

@Composable
fun MainScreen(
    launchService:() -> Unit
) {
    Column() {
        Text(text = "This is Client")
        Button(
            onClick = {
                launchService()
            }
        ) {
            Text(text = "Service")
        }
    }
}