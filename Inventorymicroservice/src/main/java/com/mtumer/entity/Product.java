package com.mtumer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="product")
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column (name="show_product")
	private boolean showProduct;
	
	@Column(name="productQty")
	private int productQty;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="productimg")
	private String productImg;
	
	@Column(name="price_per_unit")
	private double pricePerUnit;
	
	@Column(name="productdesc")
	private String productDescription;
	
	@Column(name="price_code")
	private String priceCode;

}
