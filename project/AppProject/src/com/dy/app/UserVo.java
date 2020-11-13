package com.dy.app;

public class UserVo {
	private int no;
	private String id;
	private String password;
	private String rating;
	private int products;
	private int sold;
	
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public int getProducts() {
		return products;
	}
	public void setProducts(int products) {
		this.products = products;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "ID : " + getId() + " / RATING : " + getRating();
	}
}
