package com.shoppingcart.service;

import java.math.BigDecimal;
import java.util.List;

import com.shoppingcart.data.Product;

/**
 * Shopping interface with required methods to service shopping cart
 * 
 * @author Asha
 *
 */
public interface Shopping {
	
	
	/**
	 * Returns all the products
	 * 
	 * @return products list
	 */
	List<Product> getProducts();
	
	/**
	 * Set products
	 * 
	 * @param products
	 */
	void setProducts(List<Product> products);
	
	/**
	 * Add product to the shopping list
	 * 
	 * @param name
	 * @param quantity
	 */
	void addProduct(String name, int quantity);
	
	/**
	 * Add products to the shopping products list
	 * 
	 * @param productsList
	 */
	void addProducts(List<String> productsList) ;
	
	/**
	 * Total cost of the shopping products
	 * 
	 * @return total cost of the products
	 */
	BigDecimal totalCostOfPorducts() ;
	
	/**
	 * Total cost of the shopping products after discount
	 * 
	 * @return total cost of the products
	 */
	BigDecimal totalCostOfPorductsAfterDiscount();
}
