package com.shoppingcart.data;

import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;

import com.shoppingcart.ShoppingCartConstant;

/**
 * Class representing Product
 * 
 * @author Asha
 *
 */
public class Product {
	
	//Constructor with name, cost, quantity parameters
	public Product( String name, BigDecimal cost, int quantity){
		if(StringUtils.isBlank(name)) {
			throw new IllegalArgumentException(ShoppingCartConstant.PRODUCT_MANDATORY);
		}
		
		this.name = name;
		this.cost = cost;
		this.quantity = quantity;
	}
	
	//Constructor with name and cost parameters
	public Product( String name, BigDecimal cost){
		this(name, cost, 1);
	}

	//Product name
	private String name;
	
	//Product cost
	private BigDecimal cost;
	
	//number of Products
	private int quantity;

	
	/**
	 * Get method for name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Set method for name
	 * @param name
	 */
	public void setName(String name) {
		if(StringUtils.isBlank(name)) {
			new IllegalArgumentException(ShoppingCartConstant.PRODUCT_MANDATORY);
		}
		this.name = name;
	}

	/**
	 * Get method for cost
	 * @return cost
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * Set method for cost
	 * @param cost
	 */
	/*public void setCost(BigDecimal cost) {
		this.cost = cost;
	}*/

	/**
	 * Get method for quantity
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Set method for quantity
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString(){
		return name +":"+ quantity;
	}
	
	
}
