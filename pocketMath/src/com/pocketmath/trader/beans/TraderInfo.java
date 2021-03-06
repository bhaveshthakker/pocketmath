package com.pocketmath.trader.beans;

public class TraderInfo {

	private String name;
	private String city;
	private String id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "TraderInfo [name=" + name + ", city=" + city + ", id=" + id + "]";
	}
	
	
}
