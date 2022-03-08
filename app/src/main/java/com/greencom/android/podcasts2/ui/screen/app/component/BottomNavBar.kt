package com.greencom.android.podcasts2.ui.screen.app.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsHeight
import com.greencom.android.podcasts2.ui.navigation.BottomNavBarItem
import com.greencom.android.podcasts2.ui.navigation.Screen
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private val RoutesWithBottomNavBar = setOf(
    Screen.Home.route, Screen.Discover.route, Screen.Library.route,
)

@Composable
fun BottomNavBar(
    navController: NavHostController,
    onItemReselected: (item: BottomNavBarItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationRoute = navBackStackEntry?.destination?.route
    val showBottomNavBar = currentDestinationRoute in RoutesWithBottomNavBar

    AnimatedVisibility(
        modifier = modifier,
        visible = showBottomNavBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {

        Column {
            val backgroundColor = MaterialTheme.colors.surface

            CompositionLocalProvider(LocalElevationOverlay provides null) {

                BottomNavigation(backgroundColor = backgroundColor) {

                    BottomNavBarItem.items.forEach { item ->
                        val isSelected = navBackStackEntry
                            ?.destination
                            ?.hierarchy
                            ?.any { it.route == item.route } == true

                        BottomNavigationItem(
                            icon = { Icon(imageVector = item.icon, contentDescription = null) },
                            label = { Text(stringResource(item.labelResId)) },
                            selectedContentColor = MaterialTheme.colors.primary,
                            unselectedContentColor = MaterialTheme.colors.onSurface,
                            selected = isSelected,
                            onClick = {
                                if (isSelected) {
                                    onItemReselected(item)
                                } else {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsHeight()
                    .background(backgroundColor)
            )

        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        BottomNavBar(
            navController = rememberNavController(),
            onItemReselected = {},
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun Dark() {
    PodcastsComposeTheme {
        BottomNavBar(
            navController = rememberNavController(),
            onItemReselected = {},
        )
    }
}
