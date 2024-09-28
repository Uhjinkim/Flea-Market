package com.annotation.flea.adapter.`in`

import com.annotation.flea.presentation.dto.CustomUserDetails
import com.annotation.flea.presentation.dto.ProductDTO
import com.annotation.flea.application.service.CustomUserDetailsService
import com.annotation.flea.application.service.ProductService
import com.annotation.flea.application.service.UserService
import com.annotation.flea.mapper.`in`.ProductDtoMapper
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController

@RequestMapping("/products")
class ProductController(
    private val productService: ProductService,
    private val customUserDetailsService: CustomUserDetailsService,
    private val userService: UserService,
    private val productDtoMapper: ProductDtoMapper
) {
    @GetMapping("/list")
    fun getProducts() : String {
        val products = productService.getProducts()
        products?.forEach {
            productDtoMapper.mapToProductDto(it)
        }
        val mapper = ObjectMapper()
        return mapper.writeValueAsString(products)
    }
    @PostMapping("/create")
    fun sellProduct(productDto: ProductDTO,
                    @AuthenticationPrincipal customUserDetails: CustomUserDetails
    ) : String {
        val user = userService.loadUserByUsername(customUserDetails.username) ?: return "user not found"
        val product = productDtoMapper.mapToProduct(productDto = productDto, user = user)
        productService.sellProduct(product)
        return "transaction submitted"
    }
    @PostMapping("/edit")
    fun editProduct(productDto: ProductDTO, @AuthenticationPrincipal customUserDetails: CustomUserDetails) : String {
        val user = userService.loadUserByUsername(customUserDetails.username) ?: return "user not found"
        val product = productDtoMapper.mapToProduct(productDto = productDto, user = user)
        productService.editProduct(product)
        return "product edited"
    }
}