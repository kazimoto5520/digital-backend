package com.kazimoto.digitalbackend.entity;

import com.kazimoto.digitalbackend.helper.MaiString;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends Auditable<String> implements Serializable {

    @Column(name = "row_id",unique=true)
    private String rowId= MaiString.genId();

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "lock_number")
    private String lockNumber;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "status")
    private Integer status = 1;

}
