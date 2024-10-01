package com.annotation.flea.adapter.`in`

import com.annotation.flea.domain.entity.ImageType
import com.annotation.flea.core.aws.S3Uploader
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class ImageController(
    private val imageUploader: S3Uploader,
) {

    @PostMapping("/upload/product")
    fun uploadProductImage(@RequestPart("file") files: List<MultipartFile>): ResponseEntity<String> {
        val urls = mutableListOf<String>()
        for (file in files) {
            urls.add(imageUploader.uploadFile(multipartFile = file, type = ImageType.PRODUCT))
        }
        val mapper = ObjectMapper()
        val response = mapper.writeValueAsString(urls)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/upload/profile")
    fun uploadProfileImage(@RequestPart("file") file: MultipartFile): ResponseEntity<String> {
        val url = imageUploader.uploadFile(multipartFile = file, type = ImageType.PROFILE)
        val mapper = ObjectMapper()
        val response = mapper.writeValueAsString(url)
        return ResponseEntity.ok(response)
    }
}