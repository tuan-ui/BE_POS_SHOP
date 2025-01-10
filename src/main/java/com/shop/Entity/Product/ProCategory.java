package com.shop.Entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productCategories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProCategory {
    @Transient
    public static final String SEQUENCE_NAME = "product_category_sequence";
    @Id
    private Long id;
    private String name;
}
