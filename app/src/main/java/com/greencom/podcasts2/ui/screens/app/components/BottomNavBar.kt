package com.greencom.podcasts2.ui.screens.app.components

import android.content.res.Configuration
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.greencom.podcasts2.ui.screens.app.BottomNavItem
import com.greencom.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        BottomNavItem.items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                label = { Text(stringResource(item.labelResId)) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onSurface,
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Light() {
    PodcastsComposeTheme {
        BottomNavBar(navController = rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ru")
@Composable
private fun Dark() {
    PodcastsComposeTheme {
        BottomNavBar(navController = rememberNavController())
    }
}
