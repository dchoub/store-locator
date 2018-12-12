package com.rest.store.store_locator.repositories;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rest.store.store_locator.models.Store;

public interface StoreRepository extends MongoRepository<Store, String>{
	Store findBy_id(ObjectId _id);
	List<Store> findByPostcodeNear(Point p1, Distance d1);
	List<Store> findByPostcodeIn(Circle area);
	
	
}
