package com.rest.store.store_locator.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rest.store.store_locator.models.Store;

import org.bson.types.ObjectId;

public interface StoreRepository extends MongoRepository<Store, String>{
	Store findBy_id(ObjectId _id);
	
	
}
