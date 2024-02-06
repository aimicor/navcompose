package com.aimicor.navcompose.android

import android.os.Bundle
import androidx.navigation.NavBackStackEntry
import com.aimicor.navcompose.common.navParamSpec
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BackStackEntryGetNavParamTest() {

    private val args: Bundle = Bundle()
    private val backStackEntry = mockk<NavBackStackEntry> {
        every { arguments } returns args
    }

    @Test
    fun GIVEN_no_saved_data_WHEN_get_param_or_null_called_THEN_null_returned(){
        // Given
        val param = navParamSpec<List<Int>>()

        // Then
        assertNull(backStackEntry.getNavParamOrNull(param))
    }

    @Test(expected = NavParamNotSavedException::class)
    fun GIVEN_no_saved_data_WHEN_get_param_called_THEN_error_thrown() {
        // Given
        val param = navParamSpec<Map<Int, String>>()

        // Then
        assertNull(backStackEntry.getNavParam(param))
    }

    @Test
    fun GIVEN_some_data_WHEN_get_param_called_THEN_data_returned() {
        // Given
        val param = navParamSpec<Map<String, Int>>()
        val data = mapOf("key" to 0)
        args.putString(param.key, encode(data))

        // Then
        assertEquals(data, backStackEntry.getNavParam(param))
    }

    @Test
    fun GIVEN_some_data_WHEN_get_param_or_null_called_THEN_data_returned() {
        // Given
        val param = navParamSpec<List<String>>()
        val data = listOf("key")
        args.putString(param.key, encode(data))

        // Then
        assertEquals(data, backStackEntry.getNavParamOrNull(param))
    }
}
