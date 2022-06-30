package com.greencom.android.podcasts2.ui.screen.app.component

import androidx.compose.animation.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.greencom.android.podcasts2.ui.navigation.NavigationItem

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationItemIcon(item: NavigationItem, isSelected: Boolean) {
    AnimatedContent(
        targetState = isSelected,
        transitionSpec = { scaleIn() with scaleOut() + fadeOut() },
    ) { selected ->
        val iconResId = if (selected) item.iconSelectedResId else item.iconUnselectedResId
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = stringResource(id = item.labelResId),
        )
    }
}
