package com.leemore.invservice.service;


import com.leemore.invservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository repository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
        return repository.findBySkuCode(skuCode).isPresent();

    }
}