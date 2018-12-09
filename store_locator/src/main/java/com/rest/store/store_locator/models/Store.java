package com.rest.store.store_locator.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Store {
	
	@Id	 
	public ObjectId _id;
	
	private String storeid;
	private String storename;
	private String address;
	private String postcode;
	private String latitude;
	private String longitude;
	
	
	public Store() {}
	public Store(ObjectId _id, String storeid, String storename, String address, String postcode, String latitude, String longitude) {
		this._id = _id;
		this.storeid = storeid;
		this.storename = storename;
		this.address = address;
		this.postcode = postcode;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	

}
