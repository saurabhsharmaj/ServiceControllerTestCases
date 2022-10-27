package com.example.easynotes.service;

import java.util.List;

import com.example.easynotes.exception.ProductAlreadyExistsException;
import com.example.easynotes.model.Product;

public interface ProductService {

	Product addProduct(Product product) throws ProductAlreadyExistsException;

	List<Product> getAllProducts();

	Product getProductByid(int id);

	Product deleteProductById(int id);

}