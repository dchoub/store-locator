package com.rest.store.store_locator.services;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.maps.model.LatLng;
import com.rest.store.store_locator.geocode.GeocodeService;
import com.rest.store.store_locator.models.Store;
import com.rest.store.store_locator.repositories.StoreRepository;

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
	
	

}
