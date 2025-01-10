package com.shop.Repository.Store;

import com.shop.Entity.Store.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends MongoRepository<Store,Long> {
}
