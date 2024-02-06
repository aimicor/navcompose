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
import com.aimicor.navcompose.common.NavComposeParamSpec

/**
 * Retrieves a value argument from the [SavedStateHandle] or throws an error if
 * the key value pair is not present.
 * @param param key object for the value to be retrieved.
 * @see com.aimicor.navcompose.common.navParamSpec
 * @see NavParamNotSavedException
 */
inline fun <reified T> SavedStateHandle.getNavParam(
    param: NavComposeParamSpec<T>
): T = getNavParamOrNull(param) ?: throw NavParamNotSavedException()

/**
 * Retrieves a value argument from the [SavedStateHandle] or returns a null if
 * the key value pair is not present.
 * @param param key object for the value to be retrieved.
 * @see com.aimicor.navcompose.android.core.navParamSpec
 */
inline fun <reified T> SavedStateHandle.getNavParamOrNull(
    param: NavComposeParamSpec<T>
): T? = get<String>(param.key)?.let { decode(it, param.type) }
