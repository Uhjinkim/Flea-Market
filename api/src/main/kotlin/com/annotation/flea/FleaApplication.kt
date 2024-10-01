package com.annotation.flea

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class FleaApplication

    fun main(args: Array<String>) {
        runApplication<FleaApplication>(*args)
    }
