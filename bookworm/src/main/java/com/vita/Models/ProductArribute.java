package com.vita.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductArribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_attribute_id")
    private Integer productAttributeId;

    @ManyToOne
    @JoinColumn(name = "attribute_id",nullable = true)
    private AttributeMaster attributeId;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = true)
    private ProductMaster productId;

    @Column(name = "attribute_value")
    private String attributeValue;
}