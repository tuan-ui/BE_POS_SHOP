package com.shop.Service.Product.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private int id;
    private String name;
    private BigDecimal price;
    private String categoryName;
    private Integer quantity;
}
