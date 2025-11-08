package br.pucpr.cryptotrack.ui.components

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar(selected: String, onNavigate: (String) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = selected == "home",
            onClick = { onNavigate("home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Details") },
            label = { Text("Details") },
            selected = selected == "details",
            onClick = { onNavigate("details/1") } // só exemplo, não acessa diretamente
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favoritos") },
            label = { Text("Favoritos") },
            selected = selected == "favorites",
            onClick = { onNavigate("favorites") }
        )
    }
}
