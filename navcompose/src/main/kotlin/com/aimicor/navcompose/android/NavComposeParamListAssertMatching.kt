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

import com.aimicor.navcompose.common.NavComposeParam
import com.aimicor.navcompose.common.NavComposeParamSpec
import com.aimicor.navcompose.common.NavComposeParamValue

internal fun List<NavComposeParam<*>>.assertMatching(
    params: Array<out NavComposeParamValue<*>>
) {
    map { if (it is NavComposeParamValue) it.spec else it }.apply {
        params.map { it.spec }.forEach { param ->
            if (!contains(param)) throw NavParamNotSpecifiedException()
        }
    }
    filterIsInstance<NavComposeParamSpec<*>>().forEach { paramSpec ->
        if (!params.map { it.spec }.contains(paramSpec))
            throw NavParamNotSuppliedException()
    }

}

internal fun List<NavComposeParam<*>>.assertMatching2(
    params: Array<out NavComposeParamValue<*>>
) {
    map { if (it is NavComposeParamValue) it.spec else it }.apply {
        params.map { it.spec }.forEach { param ->
            if (!contains(param)) throw NavParamNotSpecifiedException()
        }
    }
    filterIsInstance<NavComposeParamSpec<*>>().forEach { paramSpec ->
        if (!params.map { it.spec }.contains(paramSpec))
            throw NavParamNotSuppliedException()
    }

}