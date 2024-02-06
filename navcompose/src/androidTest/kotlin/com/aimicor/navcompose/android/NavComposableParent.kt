package com.aimicor.navcompose.android

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.mockk.mockkStatic
import org.junit.Rule
import kotlin.reflect.jvm.kotlinFunction

abstract class NavComposableParent {

    @get: Rule
    val composeTestRule = createComposeRule()

    protected fun testNav(
        testFunction: NavGraphBuilder.() -> Unit
    ) {
        composeTestRule.setContent {
            val testRoute = "fred"
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = testRoute
            ) {
                testFunction()
                composable(route = testRoute, arguments = emptyList()) {}
            }
        }
    }

    protected fun mockStaticNavGraphBuilderFunction(functionName: String) {
        val clazz = Class.forName("androidx.navigation.compose.NavGraphBuilderKt")
        val methods = clazz.declaredMethods
        mockkStatic(methods
            .mapNotNull { it.kotlinFunction }
            .first { it.name == functionName }
        )
    }
}
