package com.anotn.flea.write

import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel

class WriteViewModel : ViewModel() {
    var title = mutableStateOf("")
    var content = mutableStateOf("")
    var category = mutableStateOf("")
}

