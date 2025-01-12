package com.shop.Repository.Product;

import com.shop.Service.Product.DTO.ProductDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AggregationProductRepository {
    List<ProductDTO> findProducts(Pageable pageable);
}
