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

import androidx.lifecycle.SavedStateHandle
import com.aimicor.navcompose.common.navParamSpec
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SavedStateHandleGetNavParamTest {

    @Test
    fun GIVEN_no_saved_data_WHEN_get_param_or_null_called_THEN_null_returned() {
        // Given
        val savedStateHandle = SavedStateHandle()
        val param = navParamSpec<List<Int>>()

        // Then
        assertNull(savedStateHandle.getNavParamOrNull(param))
    }

    @Test(expected = NavParamNotSavedException::class)
    fun GIVEN_no_saved_data_WHEN_get_param_called_THEN_error_thrown() {
        // Given
        val savedStateHandle = SavedStateHandle()
        val param = navParamSpec<Map<Int,String>>()

        // Then
        assertNull(savedStateHandle.getNavParam(param))
    }

    @Test
    fun GIVEN_some_data_WHEN_get_param_called_THEN_data_returned() {
        // Given
        val param = navParamSpec<Map<String, Int>>()
        val data = mapOf("key" to 0)
        val savedStateHandle = SavedStateHandle(mapOf(param.key to encode(data)))

        // Then
        assertEquals(data, savedStateHandle.getNavParam(param))
    }

    @Test
    fun GIVEN_some_data_WHEN_get_param_or_null_called_THEN_data_returned() {
        // Given
        val param = navParamSpec<List<String>>()
        val data = listOf("key")
        val savedStateHandle = SavedStateHandle(mapOf(param.key to encode(data)))

        // Then
        assertEquals(data, savedStateHandle.getNavParamOrNull(param))
    }
}
