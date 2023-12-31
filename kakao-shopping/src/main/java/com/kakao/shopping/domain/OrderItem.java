package com.kakao.shopping.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderDetail orderDetail;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductOption productOption;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long price;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    protected OrderItem() {
    }

    private OrderItem(OrderDetail orderDetail, ProductOption productOption, Long quantity, Long price) {
        this.orderDetail = orderDetail;
        this.productOption = productOption;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = LocalDateTime.now();
    }

    public static OrderItem of(OrderDetail orderDetail, ProductOption productOption, Long quantity, Long price) {
        return new OrderItem(orderDetail, productOption, quantity, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem orderItem)) return false;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void update(Long quantity) {
        this.quantity = quantity;
        this.price = quantity * this.productOption.getPrice();
        this.modifiedAt = LocalDateTime.now();
    }
}
