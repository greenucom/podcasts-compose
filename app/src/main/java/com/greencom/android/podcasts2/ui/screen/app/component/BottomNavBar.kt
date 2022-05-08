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
import com.greencom.android.podcasts2.ui.common.BottomNavBarState
import com.greencom.android.podcasts2.ui.common.findBottomNavBarItemStartDestination
import com.greencom.android.podcasts2.ui.common.screenbehavior.OnBottomNavBarItemReselected
import com.greencom.android.podcasts2.ui.navigation.BottomNavBarItem
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun BottomNavBar(
    navController: NavHostController,
    state: BottomNavBarState,
    onItemReselected: OnBottomNavBarItemReselected?,
    modifier: Modifier = Modifier,
) {
    val isVisible = state == BottomNavBarState.Visible

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically { it },
        exit = slideOutVertically { it },
    ) {

        Column(modifier = modifier) {
            val backgroundColor = MaterialTheme.colors.surface

            CompositionLocalProvider(LocalElevationOverlay provides null) {

                val currentNavBackStackEntry by navController.currentBackStackEntryAsState()

                BottomNavigation(backgroundColor = backgroundColor) {

                    BottomNavBarItem.items.forEach { item ->
                        val isSelected = currentNavBackStackEntry
                            ?.destination
                            ?.hierarchy
                            ?.any { it.route == item.route } == true

                        BottomNavigationItem(
                            icon = { Icon(imageVector = item.icon, contentDescription = null) },
                            label = { Text(stringResource(item.label)) },
                            selectedContentColor = MaterialTheme.colors.primary,
                            unselectedContentColor = MaterialTheme.colors.onSurface,
                            selected = isSelected,
                            onClick = {
                                if (isSelected) {
                                    val isHandled = onItemReselected?.invoke(item)
                                    if (isHandled != true) {
                                        val itemStartDestination =
                                            navController.findBottomNavBarItemStartDestination()

                                        navController.popBackStack(
                                            destinationId = itemStartDestination.id,
                                            inclusive = false,
                                        )
                                    }
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
                Modifier
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
        val navController = rememberNavController()
        BottomNavBar(
            navController = navController,
            onItemReselected = null,
            state = BottomNavBarState.Visible,
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
        val navController = rememberNavController()
        BottomNavBar(
            navController = navController,
            onItemReselected = null,
            state = BottomNavBarState.Visible,
        )
    }
}
