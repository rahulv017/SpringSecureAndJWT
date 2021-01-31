package com.demo.spring.Model;

import javax.annotation.Generated;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="hotel")
public class Hotel {
	
	@Id
	private int id;
	private String name;
	private Address address;
	private int PricePerNight;
	
	public Address getAddress() {
		return address;
	}
	
	public void setAdddress(Address adddress) {
		this.address = adddress;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPricePerNight() {
		return PricePerNight;
	}
	public void setPricePerNight(int pricePerNight) {
		PricePerNight = pricePerNight;
	}
	
	

}
