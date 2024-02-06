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

/**
 * This exception is thrown with an attempt to navigate to a composable using
 * a parameter that was not specified in the [com.aimicor.navcompose.common.navComposableSpec]
 */
class NavParamNotSpecifiedException internal constructor() : NavComposeException(
    "Attempt to navigate to a composable using a parameter that was not specified in the navComposableSpec"
)

/**
 * This exception is thrown with an attempt to navigate to a composable without
 * a parameter specified in the [com.aimicor.navcompose.common.navComposableSpec].
 *
 */
class NavParamNotSuppliedException internal constructor() : NavComposeException(
    "Attempt to navigate to a composable without a parameter specified in the navComposableSpec"
)

/**
 * This exception is thrown when the start destination is not a direct child of this group.
 */
class StartIsNotDirectChildException internal constructor() : NavComposeException(
    "Start destination is not a direct child of this group"
)

/**
 * This exception is thrown when the start destination has a non defaulted argument parameter.
 * The argument parameters that do not have defaults cannot be set automatically by the system
 * and may result in null pointer exception down stream.
 */
class StartHasNonDefaultedParamsException internal constructor() : NavComposeException(
    "Start destination has a non defaulted argument parameter"
)

/**
 * This exception is thrown when a requested parameter is not found in an arguments list.
 */
class NavParamNotSavedException @PublishedApi internal constructor() : NavComposeException(
    "Parameter argument not found"
)

class UnexpectedParamsException @PublishedApi internal constructor() : NavComposeException(
    "Cant pass parameters to subgraphs"
)