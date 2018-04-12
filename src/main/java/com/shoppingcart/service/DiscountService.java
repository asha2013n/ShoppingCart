package com.shoppingcart.service;

import java.util.List;

import com.shoppingcart.ShoppingCartConstant;
import com.shoppingcart.data.Product;
import com.shoppingcart.data.ProductEnum;


public class DiscountService implements Discount{

	/*
	 * Applies the discounts to the final shopping list.
	 * 
	 * Current discounts:
	 * 1) Buy one get one free on Apples.
	 * 2) But three for the price of two Oranges
	 * 
	 * */
	public void applyDiscount(List<Product> products) {
		
		for(Product product: products)
		{
			int newQuantity = 0;
			switch ( ProductEnum.valueOf(product.getName())) {
	            case APPLE:
	                if (product.getQuantity() > 1) {
	                	//Buy one get one free on Apples.
	                    newQuantity = product.getQuantity() - (product.getQuantity() / 2);
	                    product.setQuantity(newQuantity);
	                }
	                break;
	
	            case ORANGE:
	                if (product.getQuantity() > 2) {
	                	//But three for the price of two Oranges
	                    newQuantity = product.getQuantity() - (product.getQuantity() / 3);
	                    product.setQuantity(newQuantity);
	                }
	                break;
	
	            default:
	                throw new IllegalArgumentException(ShoppingCartConstant.NOT_IN_LIST);
			}
			
		}
        
    }

}

