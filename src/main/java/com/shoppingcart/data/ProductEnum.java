package com.shoppingcart.data;

import java.math.BigDecimal;

import com.shoppingcart.ShoppingCartConstant;

/**
 * Product Enum
 * 
 * @author Asha
 *
 */
public enum ProductEnum {
	APPLE("APPLE", ShoppingCartConstant.APPLE_COST), 
	ORANGE("ORANGE", ShoppingCartConstant.ORANGE_COST);
	
	//Product name
	private String name;
	//Product cost
	private BigDecimal cost; 
	
	//Enum constructor taking name and cost as parameters
	private ProductEnum(String name, BigDecimal cost) { 
		this.name = name;
		this.cost = cost;
	}
	
	
	/**
	 * Get product name
	 * @return product name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set product name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * Get product cost
	 * @return product cost
	 */
	public BigDecimal getCost() {
		return cost;
	}
	
	/**
	 * Set product cost
	 * 
	 * @param cost
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}	

}

