package com.shop.Repository.Product;

import com.shop.Entity.Product.ProCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends MongoRepository<ProCategory,Long>{
}
