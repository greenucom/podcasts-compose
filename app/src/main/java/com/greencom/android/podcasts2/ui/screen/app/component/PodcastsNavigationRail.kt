package com.greencom.android.podcasts2.ui.screen.app.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.greencom.android.podcasts2.ui.navigation.NavigationItems
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme

@Composable
fun PodcastsNavigationRail(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationRail(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        NavigationItems.forEach { item ->
            val isSelected = currentDestination?.hierarchy
                ?.any { it.route == item.route } == true

            NavigationRailItem(
                selected = isSelected,
                onClick = {
                    NavigationItemUtils.onNavigationItemClicked(
                        item = item,
                        navController = navController,
                    )
                },
                icon = {
                    NavigationItemUtils.NavigationItemIcon(item = item, isSelected = isSelected)
                },
                label = { Text(text = stringResource(id = item.labelResId)) },
            )
        }
    }
}

@Composable
fun PodcastsNavigationRailRespectingWindowInsets(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Spacer(modifier = Modifier.windowInsetsStartWidth(WindowInsets.displayCutout))
        Column {
            Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
            PodcastsNavigationRail(navController = navController)
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    PodcastsTheme {
        PodcastsNavigationRail(navController = rememberNavController())
    }
}