package jpabook.jpashop.service.query;

import lombok.Getter;

@Getter
public class OrderItemDto {

    private String itemName;        // 상품명
    private int orderPrice;         // 주문가격
    private int count;              // 주문수량

    public OrderItemDto(jpabook.jpashop.domain.OrderItem orderItem) {
        this.itemName = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }

}
