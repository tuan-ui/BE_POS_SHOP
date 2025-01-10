package com.shop.Repository.Product;

import com.shop.Entity.Product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,Long> {
}
