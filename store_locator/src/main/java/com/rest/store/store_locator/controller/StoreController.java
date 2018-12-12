package com.rest.store.store_locator.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.store.store_locator.models.Store;
import com.rest.store.store_locator.services.StoreService;

@RestController
@RequestMapping("/store")
public class StoreController {
	private static Logger logger = LogManager.getLogger(StoreController.class.getName());


	@Autowired
	@Qualifier("storeService")
	private StoreService storeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Store> getAllStore() {
		logger.log(Level.INFO, "getting all stor details");
		return storeService.getAllStore();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Store getStoreById(@PathVariable("id") ObjectId id) {
		logger.log(Level.INFO, "getting store for id " +id);
		return storeService.getStoreById(id);

	}
	
	@RequestMapping(value = "/{postcode}/{distance}", method = RequestMethod.GET)
	public List<Store> getStoreById(@PathVariable("postcode") String postcode, @PathVariable("distance") double distance) {
		logger.log(Level.INFO, "finding a nearest store with postcode as " +postcode + " distance as " +distance);
		return storeService.storeNearMe(postcode, distance);

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
