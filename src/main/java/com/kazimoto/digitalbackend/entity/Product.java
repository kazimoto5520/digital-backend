package com.kazimoto.digitalbackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kazimoto.digitalbackend.helper.MaiString;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


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
