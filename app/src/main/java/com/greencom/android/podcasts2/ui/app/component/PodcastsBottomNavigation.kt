package com.greencom.android.podcasts2.ui.app.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.greencom.android.podcasts2.ui.common.navigateToNavigationItem
import com.greencom.android.podcasts2.ui.common.popBackStackToSelectedNavigationItemStartDestination
import com.greencom.android.podcasts2.ui.common.screenbehavior.LocalScreenBehaviorController
import com.greencom.android.podcasts2.ui.common.screenbehavior.NavigationBarState
import com.greencom.android.podcasts2.ui.navigation.NavigationItems
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme

@Composable
fun PodcastsBottomNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalElevationOverlay provides null) {
        val currentScreenBehavior =
            LocalScreenBehaviorController.current?.currentScreenBehaviorAsState?.value

        val isVisible = currentScreenBehavior?.navigationBarState !is NavigationBarState.Gone
        AnimatedVisibility(
            modifier = modifier,
            visible = isVisible,
            enter = slideInVertically { it },
            exit = slideOutVertically { it },
        ) {

            BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                NavigationItems.forEach { item ->
                    val isSelected = currentDestination?.hierarchy
                        ?.any { it.route == item.route } == true

                    BottomNavigationItem(
                        selected = isSelected,
                        onClick = {
                            if (isSelected) {
                                val isHandled =
                                    currentScreenBehavior?.onNavigationItemReselected?.invoke(item)
                                if (isHandled != true) {
                                    navController.popBackStackToSelectedNavigationItemStartDestination()
                                }
                            } else {
                                navController.navigateToNavigationItem(item = item)
                            }
                        },
                        icon = { NavigationItemIcon(item = item, isSelected = isSelected) },
                        label = { Text(text = stringResource(id = item.labelResId)) },
                        selectedContentColor = MaterialTheme.colors.primary,
                        unselectedContentColor = MaterialTheme.colors.onSurface
                            .copy(alpha = ContentAlpha.high),
                    )
                }
            }
        }
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    PodcastsTheme {
        PodcastsBottomNavigation(navController = rememberNavController())
    }
}
