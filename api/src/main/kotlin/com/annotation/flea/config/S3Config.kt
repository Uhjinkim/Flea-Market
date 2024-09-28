package com.annotation.flea.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

@Configuration
class S3Config(
    @Value("\${spring.cloud.aws.credentials.access-key}")
    val accessKey: String,
    @Value("\${spring.cloud.aws.credentials.secret-key}")
    val secretKey: String,
    @Value("\${spring.cloud.aws.region.static}")
    val region: String
) {
    @Bean
    fun amazonS3() : S3Client {
        val credentials = AwsBasicCredentials.create(accessKey, secretKey)
        return S3Client.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(credentials))
            .build()
    }
}