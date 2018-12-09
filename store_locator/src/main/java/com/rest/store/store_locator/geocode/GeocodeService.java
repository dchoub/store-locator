package com.rest.store.store_locator.geocode;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.rest.store.store_locator.models.Store;

public class GeocodeService {
	private GeoApiContext context;
	private String apikey;

	private void initializeGeoApiContext() {

		context = new GeoApiContext();
		context.setApiKey(apikey);
	}

	public LatLng getGeocode(Store store) {
		initializeGeoApiContext();
		GeocodingResult[] results = null;
		LatLng geocode = null;
		try {
			results = GeocodingApi.geocode(context, store.getPostcode()).await();
			geocode = results[0].geometry.location;
		} catch (Exception e) {

		}
		return geocode;
	}

	public void setContext(GeoApiContext context) {
		this.context = context;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

}
