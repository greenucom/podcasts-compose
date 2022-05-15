package com.greencom.android.podcasts2.ui.screen.app.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
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
