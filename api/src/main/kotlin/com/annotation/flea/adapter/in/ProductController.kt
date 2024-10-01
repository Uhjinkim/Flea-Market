package com.annotation.flea.adapter.`in`

import com.annotation.flea.application.service.ProductService
import com.annotation.flea.application.service.UserService
import com.annotation.flea.domain.entity.Category
import com.annotation.flea.mapper.`in`.CategoryDtoMapper
import com.annotation.flea.mapper.`in`.ProductDtoMapper
import com.annotation.flea.presentation.dto.CustomUserDetails
import com.annotation.flea.presentation.dto.ProductDTO
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController

@RequestMapping("/product")
class ProductController(
    private val productService: ProductService,
    private val userService: UserService,
    private val productDtoMapper: ProductDtoMapper,
    private val categoryDtoMapper: CategoryDtoMapper,
) {
    val mapper = jacksonObjectMapper()
    @GetMapping("/list")
    fun getProducts(@RequestParam(required = false, defaultValue = "0", value = "page") page: Int,
                    @RequestParam(required = false, defaultValue = "createdAt", value = "criteria") criteria: String,
                    @RequestParam(required = false, defaultValue = "", value = "category") category: Category,
                    ) : String {
        val products = productService.getProducts(page, criteria, category)
        products?.forEach {
            productDtoMapper.mapToProductDto(it)
        }
        return mapper.writeValueAsString(products)
    }

    @GetMapping("/new")
    fun getProductOptions() : String {
        val categories = productService.getCategoryList()
        val response = categories.map {
            categoryDtoMapper.mapToDto(it)
        }
        return mapper.writeValueAsString(response)
    }

    @PostMapping("/create")
    fun sellProduct(productDto: ProductDTO, @RequestHeader("Authorization") token: String) : String {
        val user = userService.loadUserByToken(token) ?: return "user not found"
        val product = productDtoMapper.mapToDomain(productDto = productDto, user = user)
        productService.sellProduct(product)
        return "transaction submitted"
    }
    @PostMapping("/edit")
    fun editProduct(productDto: ProductDTO, @AuthenticationPrincipal customUserDetails: CustomUserDetails) : String {
        val user = userService.loadUserByUsername(customUserDetails.username) ?: return "user not found"
        val product = productDtoMapper.mapToDomain(productDto = productDto, user = user)
        productService.editProduct(product)
        return "product edited"
    }
}