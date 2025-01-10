package com.shop.Repository.Product;

import com.shop.Service.Product.DTO.ProductDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AggregationProductRepository {
    List<ProductDTO> findProducts();
}
