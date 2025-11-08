package br.pucpr.cryptotrack.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(containerColor = Color(0xFFF3ECF9)) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Menu, contentDescription = "Home") },
            label = { Text("Home") },
            selected = navController.currentDestination?.route == "home",
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Details") },
            label = { Text("Details") },
            selected = navController.currentDestination?.route?.startsWith("details") == true,
            onClick = { /* sem ação direta — aberta por clique na moeda */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = navController.currentDestination?.route == "favorites",
            onClick = { navController.navigate("favorites") }
        )
    }
}
