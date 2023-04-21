package com.leemore.productservice.controller;


import com.leemore.productservice.dto.ProductRequest;
import com.leemore.productservice.dto.ProductResponse;
import com.leemore.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  void createproduct(@RequestBody ProductRequest productRequest){

    productService.CreateProduct(productRequest);


    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
}
