package com.aimicor.navcompose.android

/*
MIT License

Copyright (c) 2024 Aimicor Ltd. 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/


import androidx.navigation.NavController
import com.aimicor.navcompose.common.navComposableSpec
import com.aimicor.navcompose.common.navGroupRoute
import com.aimicor.navcompose.common.navParamSpec
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.Test
import kotlin.test.assertTrue

class NavControllerNavigateTest {

    private val navController = mockk<NavController> {
        every { navigate(any<String>()) } just runs
    }
    private val route = navComposableSpec()

    @Test
    fun GIVEN_a_route_WHEN_navigating_THEN_correct_route_created() {
        // Given
        val routeKey = "test1"
        val composableSpec = navComposableSpec(path = routeKey)

        // When
        navController.navigate(composableSpec)

        // Then
        verify { navController.navigate(routeKey) }
    }

    @Test
    fun GIVEN_a_group_WHEN_navigating_THEN_correct_route_created() {
        // Given
        val group = navComposableGroup(route) {}

        // When
        navController.navigate(group)

        // Then
        verify { navController.navigate(group.route) }
    }

    @Test
    fun GIVEN_a_group_route_WHEN_navigating_THEN_correct_route_created() {
        // Given
        val groupRoute = navGroupRoute()

        // When
        navController.navigate(groupRoute)

        // Then
        verify { navController.navigate(groupRoute.route) }
    }

    @Test(expected = UnexpectedParamsException::class)
    fun GIVEN_params_passed_with_group_WHEN_navigating_THEN_error_thrown() {
        // Given
        val group = navComposableGroup(route) {}
        val param = navParamSpec<Int>()

        // When
        navController.navigate(group, param with 1)
    }


        @Test
    fun GIVEN_a_default_route_WHEN_navigating_THEN_correct_route_created() {
        // When
        navController.navigate(route)

        // Then
        verify { navController.navigate(route.path) }
        assertTrue(route.path.isNotEmpty())
    }

    @Test
    fun GIVEN_a_route_and_parameter_WHEN_navigating_THEN_correct_route_created() {
        // Given
        val value = ComplexThing2()
        val paramSpec = navParamSpec<ComplexThing2>()
        val route = navComposableSpec(paramSpec)

        // When
        navController.navigate(route, paramSpec with value)

        // Then
        verify {
            navController.navigate(
                route.path +
                        "?${paramSpec.key}=${encode(value)}"
            )
        }
    }

    @Test
    fun GIVEN_a_route_and_two_parameters_WHEN_navigating_THEN_correct_route_created() {
        // Given
        val value1 = ComplexThing2()
        val paramSpec1 = navParamSpec<ComplexThing2>()
        val value2 = 123f
        val paramSpec2 = navParamSpec<Float>()
        val route = navComposableSpec(paramSpec1, paramSpec2)

        // When
        navController.navigate(route, paramSpec1 with value1, paramSpec2 with value2)

        // Then
        verify {
            navController.navigate(
                route.path +
                        "?${paramSpec1.key}=${encode(value1)}" +
                        "&${paramSpec2.key}=${encode(value2)}"
            )
        }
    }

    @Test
    fun GIVEN_a_route_and_three_parameters_WHEN_navigating_THEN_correct_route_created() {
        // Given
        val value1 = ComplexThing2()
        val paramSpec1 = navParamSpec<ComplexThing2>()
        val value2 = 123f
        val paramSpec2 = navParamSpec<Float>()
        val value3 = 234.0
        val paramSpec3 = navParamSpec<Double>()
        val route = navComposableSpec(paramSpec1, paramSpec2, paramSpec3)

        // When
        navController.navigate(
            route,
            paramSpec1 with value1, paramSpec2 with value2, paramSpec3 with value3
        )

        // Then
        verify {
            navController.navigate(
                route.path +
                        "?${paramSpec1.key}=${encode(value1)}" +
                        "&${paramSpec2.key}=${encode(value2)}" +
                        "&${paramSpec3.key}=${encode(value3)}"
            )
        }
    }

    @Test(expected = NavParamNotSpecifiedException::class)
    fun GIVEN_no_param_in_spec_WHEN_a_param_passed_THEN_error() {
        // Given
        val value1 = ComplexThing2()
        val paramSpec1 = navParamSpec<ComplexThing2>()

        // When
        navController.navigate(route, paramSpec1 with value1)
    }

    @Test(expected = NavParamNotSuppliedException::class)
    fun GIVEN_param_specified_WHEN_not_passed_THEN_error() {
        // Given
        val paramSpec1 = navParamSpec<ComplexThing2>()
        val route = navComposableSpec(paramSpec1)

        // When
        navController.navigate(route)
    }

    @Test
    fun GIVEN_defaulted_param_specified_WHEN_not_passed_THEN_no_error() {
        // Given
        val value1 = ComplexThing2()
        val paramSpec1 = navParamSpec<ComplexThing2>()
        val route = navComposableSpec(paramSpec1 with value1)

        // When
        navController.navigate(route)
    }

    @Test(expected = NavParamNotSpecifiedException::class)
    fun GIVEN_different_param_specified_WHEN_other_param_passed_THEN_error() {
        // Given
        val value1 = ComplexThing2()
        val paramSpec1 = navParamSpec<ComplexThing2>()
        val paramSpec2 = navParamSpec<ComplexThing2>()
        val route = navComposableSpec(paramSpec2 with value1)

        // When
        navController.navigate(route, paramSpec1 with value1)

    }

    @Test
    fun GIVEN_defaulted_param_specified_WHEN_param_passed_THEN_no_error() {
        // Given
        val value1 = ComplexThing2(1)
        val value2 = ComplexThing2(2)
        val paramSpec1 = navParamSpec<ComplexThing2>()
        val paramSpec2 = navParamSpec<ComplexThing2>()
        val route = navComposableSpec(paramSpec1 with value1, paramSpec2 with value1)

        // When
        navController.navigate(route, paramSpec1 with value2)

    }
}

private data class ComplexThing2(
    val int: Int = 1,
    val float: Float = 2f,
    val double: Double = 3.0,
    val list: List<String> = listOf("a", "b", "c")
)