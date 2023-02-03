package com.mtumer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtumer.entity.Product;
import com.mtumer.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	ProductRepo repo;
	
	@Autowired
	RabbitSender sender;
	
	
	public List<Product> getAllProduct(){
		List<Product> productList = repo.findAll();
		
		if(productList.size() > 0) {
			return productList;
		}else {
			return new ArrayList<Product>();
		}
	}
	
	public Product create(Product product) {
		Product newProduct = new Product();
		newProduct.setProductQty(product.getProductQty());
		newProduct.setProductName(product.getProductName());
		newProduct.setProductImg(product.getProductImg());
		newProduct.setPricePerUnit(product.getPricePerUnit());
		newProduct.setProductDescription(product.getProductDescription());
		newProduct.setPriceCode(product.getPriceCode());
		newProduct.setShowProduct(product.isShowProduct()); //added
		newProduct = repo.save(newProduct);
		if (newProduct.getProductQty()< 3) {
			sender.sendProductMessage((product.getProductName()), (product.getProductQty()));
		}
	
		return newProduct;
	}
	
	public Optional<Product> getProductById(Long id){
		return repo.findById(id);
	}

	public void update(Product product) {
		repo.saveAndFlush(product);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

}








