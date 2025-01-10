package com.shop.Service.Product;

import com.shop.Entity.Database.DatabaseSequence;
import com.shop.Entity.Product.Product;
import com.shop.Repository.Product.AggregationProductRepository;
import com.shop.Repository.Product.ProductRepository;
import com.shop.Service.Product.DTO.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private AggregationProductRepository aggregationProductRepository;

    public List<ProductDTO> getDetailedProducts() {
        return aggregationProductRepository.findProducts();
    }

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void addProduct(Product product) {
        product.setId(generateSequence(Product.SEQUENCE_NAME));
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        Product data = productRepository.findById(product.getId()).orElse(null);
        if(Objects.nonNull(data)) {
            data.setName(product.getName());
            data.setPrice(product.getPrice());
            data.setCategoryID(product.getCategoryID());
            data.setStoreID(product.getStoreID());
            productRepository.save(data);
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
