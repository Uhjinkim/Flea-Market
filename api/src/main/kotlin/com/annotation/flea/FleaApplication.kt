package com.annotation.flea

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FleaApplication

    fun main(args: Array<String>) {
        runApplication<FleaApplication>(*args)
    }
