package com.leemore.productservice.service;

import com.leemore.productservice.dto.ProductRequest;
import com.leemore.productservice.dto.ProductResponse;
import com.leemore.productservice.model.Product;
import com.leemore.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
private final ProductRepository productRepository;



    public void CreateProduct(ProductRequest productRequest ){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved",product.getId() );
    }


    public List<ProductResponse> getAllProducts() {
        List<Product> prods = productRepository.findAll();
        return prods.stream().map(this::mapToProductResponse).toList();


    }

    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
