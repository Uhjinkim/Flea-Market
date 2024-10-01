package com.annotation.flea.core.aws

import com.annotation.flea.domain.entity.ImageType
import io.awspring.cloud.s3.ObjectMetadata
import io.awspring.cloud.s3.S3Template
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class S3Uploader(
    private val s3Template: S3Template,
    @Value("\${aws.s3.bucket}")
    private val bucketName: String,
    private val imageUtil: ImageUtil,
) {
    fun uploadFile(
        multipartFile: MultipartFile, type: ImageType
    ) : String {
        val folder = when(type) {
            ImageType.PROFILE -> "profile-images/"
            ImageType.PRODUCT -> "product-images/"
        }
        // Check if the file is empty
        if(multipartFile.isEmpty) {
            throw RuntimeException("file is empty")
        }
        // Build the file name
        val fileName = imageUtil.buildFileName(multipartFile.originalFilename!!)
        // Upload the file to S3

        val s3Resource = s3Template.upload(
            bucketName,
            folder+fileName,
            multipartFile.inputStream,
            ObjectMetadata.builder()
                .contentType(multipartFile.contentType)
                .build()
            )
        val url = s3Resource.url.toString()
        return url
    }
}