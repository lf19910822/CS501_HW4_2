package com.example.hw4_2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CounterScreen(
    viewModel: CounterViewModel,
    onNavigateToSettings: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Counter++",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "${state.count}",
            style = MaterialTheme.typography.displayLarge,
            fontSize = 72.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Auto mode: ${if (state.isAutoMode) "ON" else "OFF"}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { viewModel.decrement() },
                modifier = Modifier.width(80.dp)
            ) {
                Text("-1")
            }

            Button(
                onClick = { viewModel.increment() },
                modifier = Modifier.width(80.dp)
            ) {
                Text("+1")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.reset() },
            modifier = Modifier.width(176.dp)
        ) {
            Text("Reset")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.toggleAutoMode() },
            modifier = Modifier.width(176.dp),
            colors = if (state.isAutoMode)
                ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            else
                ButtonDefaults.buttonColors()
        ) {
            Text(if (state.isAutoMode) "Stop Auto" else "Start Auto")
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(
            onClick = onNavigateToSettings,
            modifier = Modifier.width(176.dp)
        ) {
            Text("Settings")
        }
    }
}

