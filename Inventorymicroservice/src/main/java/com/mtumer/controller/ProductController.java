package com.mtumer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtumer.entity.Product;
import com.mtumer.service.ProductService;
import com.mtumer.service.RabbitSender;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@Autowired
	RabbitSender sender;
	
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> products = service.getAllProduct();
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") Long id) {
		Optional<Product> product = service.getProductById(id);
		if(!product.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<>(product.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		Product saved = service.create(product);
		return new ResponseEntity<Product>(saved, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody Product product){
		Optional<Product> update = service.getProductById(id);
		if(!update.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Product updated = new Product();
		updated.setId(id);
		updated.setPricePerUnit(product.getPricePerUnit());
		updated.setProductDescription(product.getProductDescription());
		updated.setProductImg(product.getProductImg());
		updated.setProductName(product.getProductName());
		updated.setProductQty(product.getProductQty());
		updated.setPriceCode(product.getPriceCode());
		updated.setShowProduct(product.isShowProduct());
		service.update(updated);
		
		if (updated.getProductQty() < 3) {
			sender.sendUpdateMessage(id, updated.getProductName(), updated.getProductQty());
		}
		
		
		return new ResponseEntity<>(updated, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product>delete(@PathVariable("id")Long id) {
		Optional<Product> removed = service.getProductById(id);
		if(!removed.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	

}













