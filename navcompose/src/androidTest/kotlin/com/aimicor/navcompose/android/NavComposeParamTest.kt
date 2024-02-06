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

import com.aimicor.navcompose.TestData
import com.aimicor.navcompose.common.navParamSpec
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class NavComposeParamSpecTest {

    private val testData = TestData(
        1,
        1.0f,
        12.0,
        "https://www.youtube.com/watch?v=qM5W7Xn7FiA&list=RDqM5W7Xn7FiA&start_radio=1"
    )

    @Test
    fun WHEN_two_ComposeParamSpecs_of_same_type_are_generated_one_with_overridden_key_one_without_THEN_they_will_be_different() {
        // When
        val sut1 = navParamSpec<Int>("test1")
        assertEquals("test1", sut1.key)

        // And
        val sut2 = navParamSpec<Int>()
        assertTrue(sut2.key.isNotEmpty())

        // Then
        assertNotEquals(sut1, sut2)
    }

    @Test
    fun GIVEN_an_object_of_complex_data_WHEN_encoded_THEN_same_data_will_be_decoded_using_NavParamSpec() {
        // Given
        val sut1 = navParamSpec<TestData>()

        // When
        val converted: String = encode(testData)

        // Then
        assertTrue(converted.isNotEmpty())
        assertEquals(testData, decode(converted, sut1.type))
    }

    @Test
    fun GIVEN_a_list_of_complex_data_WHEN_encoded_THEN_same_data_will_be_decoded_using_NavParamSpec() {
        // Given
        val dataList = listOf(testData, testData)
        val sut1 = navParamSpec<List<TestData>>()

        // When
        val converted: String = encode(dataList)

        // Then
        assertEquals(dataList, decode(converted, sut1.type))
    }

    @Test
    fun GIVEN_a_map_of_complex_data_WHEN_encoded_THEN_same_data_will_be_decoded_using_NavParamSpec() {
        // Given
        val dataMap = mapOf("x" to testData, "y" to testData)
        val sut1 = navParamSpec<Map<String, TestData>>()

        // When
        val converted: String = encode(dataMap)

        // Then
        assertEquals(dataMap, decode(converted, sut1.type))
    }

    @Test
    fun GIVEN_an_object_of_complex_data_containing_a_url_WHEN_encoded_THEN_url_will_not_interfere_with_path() {
        assertEquals(1, encode(testData).split("/").size)
    }
}
