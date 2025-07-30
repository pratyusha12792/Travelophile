package com.pratyusha.travelophile.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.pratyusha.travelophile.BottomNavItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun AnimatedBottomNavBar(
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = index == selectedIndex
            val bubbleSize by animateDpAsState(if (isSelected) 48.dp else 0.dp)
            val iconColor by animateColorAsState(
                if (isSelected) Color(0xFF065D5D)
                else MaterialTheme.colorScheme.onSurfaceVariant
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) {
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .size(bubbleSize)
                            .background(
                                color = Color(0xFF065D5D).copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                    )
                }
                IconButton(
                    onClick = {
                        onItemSelected(index)
                    },
                    interactionSource = NoRippleInteractionSource
                ) {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title,
                        tint = iconColor,
                        modifier = Modifier
                            .size(24.dp)
                            .zIndex(1f)
                    )
                }
            }
        }
    }
}
private object NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}