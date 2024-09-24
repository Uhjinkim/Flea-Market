package com.anotn.flea.data.repository

interface CategoryRepository {
    suspend fun getCategories(): List<String>
}