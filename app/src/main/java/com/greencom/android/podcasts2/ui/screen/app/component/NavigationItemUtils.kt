package com.greencom.android.podcasts2.ui.screen.app.component

import androidx.compose.animation.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.greencom.android.podcasts2.ui.navigation.NavigationItem

object NavigationItemUtils {

    fun onNavigationItemClicked(item: NavigationItem, navController: NavHostController) {
        navController.navigate(item.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun NavigationItemIcon(item: NavigationItem, isSelected: Boolean) {
        AnimatedContent(
            targetState = isSelected,
            transitionSpec = { scaleIn() with scaleOut() + fadeOut() },
        ) { selected ->
            val iconResId = if (selected) item.iconSelectedResId else item.iconUnselectedResId
            Icon(
                painter = painterResource(iconResId),
                contentDescription = stringResource(item.labelResId),
            )
        }
    }

}
