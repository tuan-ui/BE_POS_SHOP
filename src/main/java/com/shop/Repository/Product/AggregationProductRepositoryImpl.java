package com.shop.Repository.Product;

import com.shop.Service.Product.DTO.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AggregationProductRepositoryImpl implements AggregationProductRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ProductDTO> findProducts() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.lookup("productCategories", "categoryID", "_id", "categoryDetails"),
                Aggregation.unwind("categoryDetails", true),
                Aggregation.lookup("stores", "storeID", "_id", "storeDetails"),
                Aggregation.unwind("storeDetails", true),
                Aggregation.project("name","price")
                        .and("categoryDetails.name").as("categoryName")
                        .and("storeDetails.quantity").as("quantity")
        );

        AggregationResults<ProductDTO> results = mongoTemplate.aggregate(aggregation, "products", ProductDTO.class);
        return results.getMappedResults();
    }
}
