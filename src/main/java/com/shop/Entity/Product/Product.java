package com.shop.Entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Transient
    public static final String SEQUENCE_NAME = "product_sequence";
    @Id
    private Long id;
    private String name;
    private BigDecimal price;
    private Long categoryID;
    private Long storeID;
}
