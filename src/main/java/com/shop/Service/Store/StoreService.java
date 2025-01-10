package com.shop.Service.Store;

import com.shop.Entity.Database.DatabaseSequence;
import com.shop.Entity.Product.ProCategory;
import com.shop.Entity.Store.Store;
import com.shop.Repository.Store.StoreRepository;
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
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public List<Store> getAllStore() {
        return storeRepository.findAll();
    }

    public Store getStore(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public void addStore(Store store) {
        store.setId(generateSequence(Store.SEQUENCE_NAME));
        storeRepository.save(store);
    }

    public void updateStore(Store store) {
        Store data = storeRepository.findById(store.getId()).orElse(null);
        if(Objects.nonNull(data)) {
            data.setQuantity(store.getQuantity());
            storeRepository.save(data);
        }
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }
}
