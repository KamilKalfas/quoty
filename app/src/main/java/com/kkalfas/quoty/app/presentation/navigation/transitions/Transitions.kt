package com.kkalfas.quoty.app.presentation.navigation.transitions

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalAnimationApi::class)
val defaultEnterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition =
    {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(100))
    }

@OptIn(ExperimentalAnimationApi::class)
val defaultExitTransition: AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition =
    {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(100))
    }

@OptIn(ExperimentalAnimationApi::class)
val defaultPopEnterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition =
    {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(100))
    }

@OptIn(ExperimentalAnimationApi::class)
val defaultPopExitTransition: AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition =
    {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(100))
    }
