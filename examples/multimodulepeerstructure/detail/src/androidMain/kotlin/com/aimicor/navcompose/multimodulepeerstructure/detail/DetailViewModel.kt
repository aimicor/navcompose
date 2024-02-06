package com.aimicor.navcompose.multimodulepeerstructure.detail

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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aimicor.navcompose.android.getNavParam
import com.aimicor.navcompose.multimodulepeerstructure.navcompose.ComplexStuff
import com.aimicor.navcompose.multimodulepeerstructure.navcompose.detailParam

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    val receivedParam : ComplexStuff = savedStateHandle.getNavParam(detailParam)
) : ViewModel() {

    // This is not necessary when using Hilt or Koin
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                DetailViewModel(
                    savedStateHandle = createSavedStateHandle()
                )
            }
        }
    }
}