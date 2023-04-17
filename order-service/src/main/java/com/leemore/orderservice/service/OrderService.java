package com.leemore.orderservice.service;

import com.leemore.orderservice.dto.InventoryResponse;
import com.leemore.orderservice.dto.OrderLineItemsListDto;
import com.leemore.orderservice.dto.OrderRequest;
import com.leemore.orderservice.model.OrderLineItems;
import com.leemore.orderservice.model.Orders;
import com.leemore.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){
        Orders order = new Orders();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> listItem = orderRequest.getLineItemsListDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();


        order.setOrderLineItemsList(listItem);

        List<String> skucodesList = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        //call inventory service, place the order if the product is in stock
        //http://localhost:8082/api/inventory?skuCode=iphone_123?skuCode=samsung_347
        InventoryResponse[] inventoryResponsesArray = webClient.get()
                .uri("http://localhost:8082/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skucodesList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductsInStock = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInStock);

        if(allProductsInStock){
            repository.save(order);
        }
        else {
            throw new IllegalArgumentException("Product not in stock, please try again later");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsListDto orderLineItems) {
        OrderLineItems orderLineItems1= new OrderLineItems();
        orderLineItems1.setPrice(orderLineItems.getPrice());
        orderLineItems1.setQuantity(orderLineItems.getQuantity());
        orderLineItems1.setSkuCode(orderLineItems.getSkuCode());

        return orderLineItems1;
    }
}
