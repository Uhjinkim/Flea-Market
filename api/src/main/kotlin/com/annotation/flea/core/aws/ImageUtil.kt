package com.annotation.flea.core.aws

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ImageUtil {
    private final val fileExtensionSeparator = "."

    fun getFileName(originalFilename: String): String {
        val fileExtensionIndex = originalFilename.lastIndexOf(fileExtensionSeparator)
        return originalFilename.substring(0, fileExtensionIndex)
    }
    fun buildFileName(originalFilename: String): String {
        val fileExtensionIndex = originalFilename.lastIndexOf(fileExtensionSeparator)
        val fileName = UUID.randomUUID().toString()
        val fileExtension = originalFilename.substring(fileExtensionIndex)
        val timeStamp = System.currentTimeMillis()
        return "${fileName}_${timeStamp}$fileExtension"
    }
}