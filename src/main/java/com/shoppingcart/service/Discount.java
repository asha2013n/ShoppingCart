package com.shoppingcart.service;


import java.util.List;
import com.shoppingcart.data.Product;

public interface Discount {
	
	/*
	 * Applies the discounts to the final shopping list.
	 * 
	 * Current discounts:
	 * 1) Buy one get one free on Apples.
	 * 2) But three for the price of two Oranges
	 * 
	 * */
	void applyDiscount(List<Product> products);

}
