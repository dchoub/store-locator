package com.rest.store.store_locator.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;

import com.google.maps.model.LatLng;
import com.rest.store.store_locator.geocode.GeocodeService;
import com.rest.store.store_locator.models.Store;
import com.rest.store.store_locator.repositories.StoreRepository;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;


public class StoreService {
	@Autowired
	private StoreRepository repository;
	
	@Autowired
	@Qualifier("geocodeService")
	private GeocodeService geocodeService;
	
	public List<Store> getAllStore() {
		return repository.findAll();
	}
	
	public Store getStoreById(ObjectId id) {
		return repository.findBy_id(id);

	}
	
	public void deleteStore(ObjectId id) {
		repository.delete(repository.findBy_id(id));
	}
	
	public void modifyStoreById(ObjectId id, Store store) {
		store.set_id(id);
		repository.save(store);
	}
	
	
	public Store createStore(Store store) {
		LatLng geocode = geocodeService.getGeocode(store);
		if (null != geocode) {
			store.setLatitude(geocode.lat);
			store.setLongitude(geocode.lng);
		}
		store.set_id(ObjectId.get());
		repository.save(store);
		return store;
	}
	
	// not working code, yet to be implemented completely 
	public List<Store> storeNearMe(String postcode, double distance) {
		LatLng geocodeForNearest = geocodeService.getGeocodeByPostcode(postcode);
		Double lat = geocodeForNearest.lat;
		Double lng = geocodeForNearest.lng;
		
		int intLat =(int)(lat * 1E6);
		int intLng =(int)(lng * 1E6);
		Circle a =  new Circle (new Point(intLat, intLng), new Distance(distance, Metrics.KILOMETERS));
		
	//return repository.findByPostcodeNear(new Point(intLat, intLng), new Distance(distance, Metrics.KILOMETERS));
		return repository.findByPostcodeIn(a);
		
//		return repository.findByPostcodeNear(
//				 new Point(Double.valueOf(longitude), Double.valueOf(latitude)),
//			      new Distance(distance, Metrics.KILOMETERS));
//		
		
	}
	
	
	

}
