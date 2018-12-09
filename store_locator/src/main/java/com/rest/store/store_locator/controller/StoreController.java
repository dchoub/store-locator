package com.rest.store.store_locator.controller;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.store.store_locator.models.Store;
import com.rest.store.store_locator.repositories.StoreRepository;

@RestController
@RequestMapping("/store")
public class StoreController {
	@Autowired

	private StoreRepository repository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Store> getAllStore() {
		return repository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Store getStoreById(@PathVariable("id") ObjectId id) {
		return repository.findBy_id(id);

	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Store createStore(@Valid @RequestBody Store store) {
		store.set_id(ObjectId.get());
		repository.save(store);
		return store;
	}

	@RequestMapping(value = "/{storeid}", method = RequestMethod.PUT)
	public void modifyStoreById(@PathVariable("storeid") String storeid, @Valid @RequestBody Store store) {
		store.setStoreid(storeid);
		// store.set_id(storeid);
		repository.save(store);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteStore(@PathVariable ObjectId id) {
	  repository.delete(repository.findBy_id(id));
	}
	
	

}
