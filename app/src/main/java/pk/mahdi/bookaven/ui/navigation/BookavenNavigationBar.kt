package pk.mahdi.bookaven.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import pk.mahdi.bookaven.designsystem.theme.figeronaFont

@Composable
fun BookavenNavigationBar(
    destinations: List<RootDestination>,
    currentDestination: NavDestination?,
    onNavigationSelected: (RootScreen) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            destinations.forEach { destination ->
                val selected = currentDestination.isRootDestination(destination)

                CustomBottomNavigationItem(
                    rootDestination = destination,
                    isSelected = selected,
                    onClick = { onNavigationSelected(destination.rootScreen) },
                )
        }

        }
    }
}


@Composable
private fun CustomBottomNavigationItem(
    rootDestination: RootDestination,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val background =
        if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else Color.Transparent
    val contentColor =
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Icon(
                imageVector = ImageVector.vectorResource(id = rootDestination.selectedIcon),
                contentDescription = rootDestination.title,
                tint = contentColor
            )

            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = rootDestination.title,
                    color = contentColor,
                    fontFamily = figeronaFont,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}


private fun NavDestination?.isRootDestination(destination: RootDestination) =
    this?.hierarchy?.any { it.route == destination.rootScreen.destinationRoute.route } ?: false
