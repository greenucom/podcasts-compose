package com.greencom.android.podcasts2.ui.screen.app.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.greencom.android.podcasts2.ui.common.navigateToNavigationItem
import com.greencom.android.podcasts2.ui.common.popBackStackToSelectedNavigationItemStartDestination
import com.greencom.android.podcasts2.ui.common.screenbehavior.LocalScreenBehaviorController
import com.greencom.android.podcasts2.ui.navigation.NavigationItems
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

@Composable
fun PodcastsNavigationRail(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Row {
        NavigationRail(
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 0.dp,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val currentScreenBehavior =
                LocalScreenBehaviorController.current?.currentScreenBehaviorAsState?.value

            NavigationItems.forEach { item ->
                val isSelected = currentDestination?.hierarchy
                    ?.any { it.route == item.route } == true

                NavigationRailItem(
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
                    alwaysShowLabel = false,
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface
                        .copy(alpha = ContentAlpha.high),
                )
            }
        }

        val dividerColor = MaterialTheme.colors.onSurfaceUtil
        Spacer(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .drawBehind { drawRect(color = dividerColor) }
        )
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
