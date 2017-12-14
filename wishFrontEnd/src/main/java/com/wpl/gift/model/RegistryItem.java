
package com.wpl.gift.model;


/**
 * Author Manohar, Sneha
 */
public class RegistryItem {

	private int id;
	private String name;
	private float price;
	private int registryId;
	private int productId;
	private String category;
	private String color;
	private int rating;
	private String assigned;

	public RegistryItem() {
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getRegistryId() {
		return registryId;
	}

	public void setRegistryId(int registryId) {
		this.registryId = registryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getAssigned() {
		return assigned;
	}

	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}

	public void toRegistryItem(Product product) {
		this.productId = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.category = product.getCategory();
		this.color = product.getColor();
		this.rating = product.getRating();
	}
}
