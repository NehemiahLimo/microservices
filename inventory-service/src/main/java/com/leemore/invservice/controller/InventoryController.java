package com.leemore.invservice.controller;


import com.leemore.invservice.service.InventoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor

public class InventoryController {

    private final InventoryService service;

    @PostMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode){
        return service.isInStock(skuCode);
    }
}
