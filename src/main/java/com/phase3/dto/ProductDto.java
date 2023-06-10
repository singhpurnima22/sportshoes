package com.phase3.dto;

import com.phase3.model.Category;

public class ProductDto {
	private Long id;
    private String name;
    private int category_id;
	private double price;
	private double weight;
	private String description;
	private String imagename;
	
	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDto(Long id, String name, int categoryId, double price, double weight, String description,
			String imagename) {
		super();
		this.id = id;
		this.name = name;
		this.category_id = category_id;
		this.price = price;
		this.weight = weight;
		this.description = description;
		this.imagename = imagename;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategoryId(int category_id) {
		this.category_id = category_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", categoryId=" + category_id + ", price=" + price
				+ ", weight=" + weight + ", description=" + description + ", imagename=" + imagename + "]";
	}
	
}
