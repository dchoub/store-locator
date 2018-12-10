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
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;
import com.rest.store.store_locator.geocode.GeocodeService;
import com.rest.store.store_locator.models.Store;
import com.rest.store.store_locator.repositories.StoreRepository;
import com.rest.store.store_locator.services.StoreService;

@RestController
@RequestMapping("/store")
public class StoreController {

	@Autowired
	@Qualifier("storeService")
	private StoreService storeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Store> getAllStore() {
		return storeService.getAllStore();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Store getStoreById(@PathVariable("id") ObjectId id) {
		return storeService.getStoreById(id);

	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Store createStore(@Valid @RequestBody Store store) {
		return storeService.createStore(store);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void modifyStoreById(@PathVariable("id") ObjectId id, @Valid @RequestBody Store store) {
		storeService.modifyStoreById(id, store);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteStore(@PathVariable ObjectId id) {
		storeService.deleteStore(id);
	}

}
