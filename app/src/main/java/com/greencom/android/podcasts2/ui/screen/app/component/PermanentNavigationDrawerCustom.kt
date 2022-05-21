package com.greencom.android.podcasts2.ui.screen.app.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermanentNavigationDrawerCustom(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    PermanentNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            NavigationItems.forEach { item ->
                val isSelected = currentDestination?.hierarchy
                    ?.any { it.route == item.route } == true

                NavigationDrawerItem(
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
        },
        content = content,
    )
}

@Composable
fun PermanentNavigationDrawerCustomRespectingWindowInsets(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Row(modifier = modifier) {
        Spacer(modifier = Modifier.windowInsetsStartWidth(WindowInsets.displayCutout))
        Column {
            Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
            PermanentNavigationDrawerCustom(navController = navController, content = content)
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Composable
@Preview(name = "Light", widthDp = 1240, heightDp = 800)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp = 1240, heightDp = 800)
private fun Preview() {
    PodcastsTheme {
        Surface {
            PermanentNavigationDrawerCustom(navController = rememberNavController()) {}
        }
    }
}
