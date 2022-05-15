package com.greencom.android.podcasts2.ui.screen.app.component

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.greencom.android.podcasts2.ui.navigation.NavigationItems
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme

private val NavigationBarTonalElevation = 3.dp

// TODO: Follow DRY
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationRailCustom(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationRail(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        NavigationItems.forEach { item ->
            val isSelected = currentDestination?.hierarchy
                ?.any { it.route == item.route } == true

            val onClick = {
                navController.navigate(item.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }

            val icon = @Composable {
                AnimatedContent(
                    targetState = isSelected,
                    transitionSpec = { scaleIn() with scaleOut() + fadeOut() },
                ) { isSelected ->
                    val iconResId = if (isSelected) item.iconSelectedResId else item.iconUnselectedResId
                    Icon(
                        painter = painterResource(iconResId),
                        contentDescription = stringResource(item.labelResId),
                    )
                }
            }

            NavigationRailItem(
                selected = isSelected,
                onClick = onClick,
                icon = icon,
                label = { Text(text = stringResource(item.labelResId)) },
            )
        }
    }
}

@Composable
fun NavigationRailCustomWithSystemBars(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        NavigationRailCustom(navController)
        Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    PodcastsTheme {
        NavigationRailCustom(navController = rememberNavController())
    }
}
