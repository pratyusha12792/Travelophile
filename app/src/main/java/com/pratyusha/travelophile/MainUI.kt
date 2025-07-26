package com.pratyusha.travelophile

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.rotary.RotaryScrollEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pratyusha.travelophile.ui.AnimatedBottomNavBar
import com.pratyusha.travelophile.ui.screen.HomeScreen

data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUI(){
    var selectedIndex by rememberSaveable {mutableStateOf(0)}
    val items = listOf(
        BottomNavItem(
            title = "Home",
            selectedIcon = Icons.Default.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavItem(
            title = "",
            selectedIcon = Icons.Default.Map,
            unselectedIcon = Icons.Outlined.Map
        ),
        BottomNavItem(
            title = "Bookmark",
            selectedIcon = Icons.Default.Bookmark,
            unselectedIcon = Icons.Outlined.BookmarkBorder

        ),
        BottomNavItem(
            title = "Profile",
            selectedIcon = Icons.Default.Person,
            unselectedIcon = Icons.Outlined.Person
        )
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .padding(end = 16.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(onClick = {}) {
                            Image(
                                painter = painterResource(R.drawable.cat),
                                contentDescription = "Profile",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically){
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location",
                                modifier = Modifier.size(30.dp),
                                tint = Color(0xFF065D5D)
                            )
                        }
                        Spacer(modifier = Modifier.width(0.dp))
                        Text(
                            text = "New Delhi, India",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF065D5D)
                        )
                    }
                }
            )
        },
        bottomBar = {
            AnimatedBottomNavBar(
                items = items,
                selectedIndex = selectedIndex,
                onItemSelected = { selectedIndex = it }
            )
            Spacer(modifier = Modifier.height(100.dp))
        }
    ) {
        innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ){
            HomeScreen()
        }
    }
}