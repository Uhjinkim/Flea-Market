package com.annotation.flea.presentation.dto

import java.util.UUID

data class UserAddressDTO(
    val seq: UUID?,
    val roadAddress: String,
    val lotAddress: String,
    val detailAddress: String,
)