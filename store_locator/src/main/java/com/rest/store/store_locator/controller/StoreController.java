package com.rest.store.store_locator.controller;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;
import com.rest.store.store_locator.geocode.GeocodeService;
import com.rest.store.store_locator.models.Store;
import com.rest.store.store_locator.repositories.StoreRepository;

@RestController
@RequestMapping("/store")
public class StoreController {
	@Autowired
	//@Qualifier("repository")
	private StoreRepository repository;
	
	@Autowired
	@Qualifier("geocodeService")
	private GeocodeService geocodeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Store> getAllStore() {
		return repository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Store getStoreById(@PathVariable("id") ObjectId id) {
		return repository.findBy_id(id);

	}

	
	//for adding a store
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Store createStore(@Valid @RequestBody Store store) {
		LatLng geocode = geocodeService.getGeocode(store);
		if (null != geocode) {
			store.setLatitude(geocode.lat);
			store.setLongitude(geocode.lng);
		}
		store.set_id(ObjectId.get());
		repository.save(store);
		return store;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void modifyStoreById(@PathVariable("id") ObjectId id, @Valid @RequestBody Store store) {
		store.set_id(id);
		repository.save(store);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteStore(@PathVariable ObjectId id) {
		repository.delete(repository.findBy_id(id));
	}

}
