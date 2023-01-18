package jpabook.jpashop.service.query;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class OrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order o) {
        this.orderId = o.getId();
        this.name= o.getMember().getName();
        this.orderStatus = o.getStatus();
        this.orderDate = o.getOrderDate();
        this.address = o.getDelivery().getAddress();
        orderItems = o.getOrderItems().stream()
                .map(OrderItemDto::new)
                .collect(toList());
    }

}
