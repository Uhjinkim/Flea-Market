package com.anotn.flea.feed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.anotn.flea.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject


class FeedViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

}