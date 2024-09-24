package com.anotn.flea.write

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.anotn.flea.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject


class WriteViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    var title = mutableStateOf("")
    var content = mutableStateOf("")
    var category = mutableStateOf("")
}

