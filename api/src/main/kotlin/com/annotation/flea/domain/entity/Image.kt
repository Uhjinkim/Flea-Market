package com.annotation.flea.domain.entity

import java.time.LocalDateTime

data class Image(
    val imageUrl: String,
    val type: ImageType,
    val owner: User,
    )