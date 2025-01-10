package com.shop.Service.Product;

import com.shop.Entity.Database.DatabaseSequence;
import com.shop.Entity.Product.ProCategory;
import com.shop.Repository.Product.ProductCategoryRepository;
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
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public List<ProCategory> getAllProductCategory() {
        return productCategoryRepository.findAll();
    }

    public ProCategory getProductCategory(Long id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    public void addProductCategory(ProCategory productCategory) {
        productCategory.setId(generateSequence(ProCategory.SEQUENCE_NAME));
        productCategoryRepository.save(productCategory);
    }

    public void updateProductCategory(ProCategory productCategory) {
        ProCategory data = productCategoryRepository.findById(productCategory.getId()).orElse(null);
        if(Objects.nonNull(data)) {
            data.setName(productCategory.getName());
            productCategoryRepository.save(data);
        }
    }

    public void deleteProductCategory(Long id) {
        productCategoryRepository.deleteById(id);
    }
}
