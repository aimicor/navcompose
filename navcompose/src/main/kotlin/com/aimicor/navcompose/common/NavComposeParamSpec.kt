package com.aimicor.navcompose.common

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

import java.lang.reflect.Type

/**
 * An argument parameter key class that stores information about the class of data to be passed.
 * Requires a factory function to create.
 * @param key unique identifier for the argument.
 * @param type the type of argument being passed.
 * @see navParamSpec
 */
data class NavComposeParamSpec<T> @PublishedApi internal constructor(
    @PublishedApi override val key: String,
    @PublishedApi internal val type: Type
) : NavComposeParam<T>(key) {
    /**
     * Creates a [NavComposeParamValue]
     *  Use with [com.aimicor.navcompose.android.navigate] and [navComposableSpec]
     */
    infix fun with(value: T?) = NavComposeParamValue(this, value)
}