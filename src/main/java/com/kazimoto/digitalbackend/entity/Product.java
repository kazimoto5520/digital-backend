package com.kazimoto.digitalbackend.entity;

import com.kazimoto.digitalbackend.helper.MaiString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends Auditable<String> implements Serializable {

    @Column(name = "row_id",unique=true)
    private String rowId= MaiString.genId();

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private Integer status = 1;
}
