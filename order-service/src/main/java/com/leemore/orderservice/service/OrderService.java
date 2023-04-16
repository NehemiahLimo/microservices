package com.leemore.orderservice.service;

import com.leemore.orderservice.dto.OrderLineItemsListDto;
import com.leemore.orderservice.dto.OrderRequest;
import com.leemore.orderservice.model.OrderLineItems;
import com.leemore.orderservice.model.Orders;
import com.leemore.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;


    public void placeOrder(OrderRequest orderRequest){
        Orders order = new Orders();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> listItem = orderRequest.getLineItemsListDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();


        order.setOrderLineItemsList(listItem);
        repository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsListDto orderLineItems) {
        OrderLineItems orderLineItems1= new OrderLineItems();
        orderLineItems1.setPrice(orderLineItems.getPrice());
        orderLineItems1.setQuantity(orderLineItems.getQuantity());
        orderLineItems1.setSkuCode(orderLineItems.getSkuCode());

        return orderLineItems1;
    }
}
