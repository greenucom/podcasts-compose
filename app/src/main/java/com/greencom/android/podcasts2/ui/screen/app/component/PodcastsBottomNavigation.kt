package com.greencom.android.podcasts2.ui.screen.app.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.greencom.android.podcasts2.ui.navigation.NavigationItems
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme

@Composable
fun PodcastsBottomNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalElevationOverlay provides null) {

        BottomNavigation(
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            NavigationItems.forEach { item ->
                val isSelected = currentDestination?.hierarchy
                    ?.any { it.route == item.route } == true

                BottomNavigationItem(
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
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface,
                )
            }
        }
    }
}

@Composable
fun PodcastsBottomNavigationRespectingWindowInsets(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        PodcastsBottomNavigation(navController = navController)

        val spacerColor = MaterialTheme.colors.surface
        Spacer(
            modifier = Modifier
                .windowInsetsBottomHeight(WindowInsets.navigationBars)
                .fillMaxWidth()
                .drawBehind { drawRect(color = spacerColor) }
        )
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
