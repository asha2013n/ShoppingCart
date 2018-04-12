package com.shoppingcart.service;

import com.shoppingcart.ShoppingCartConstant;
import com.shoppingcart.data.Product;
import com.shoppingcart.data.ProductEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * Shopping service implementing all required methods for shopping cart
 * 
 * @author Asha
 * 
 */
public class ShoppingService implements Shopping{
	
	final static Logger logger = Logger.getLogger(ShoppingService.class);

		
	//List of products
	private List<Product> products = new ArrayList<>();
	
	/**
	 * Returns all the products
	 * 
	 * @return products list
	 */
	public List<Product> getProducts() {		
		return products;
	}
	
	/**
	 * Set products
	 * 
	 * @param products
	 */
	public void setProducts(List<Product> products) {
		if(products == null){
			products = new ArrayList<Product>();
		}
		this.products = products;
	}
	
	/**
	 * Add product to the shopping list
	 * 
	 * @param name
	 * @param quantity
	 */
	public void addProduct(final String name, final int quantity) {
		
		//checks if product name is provided
		if(StringUtils.isBlank(name)) {
			logger.error(ShoppingCartConstant.PRODUCT_MANDATORY);
			throw new IllegalArgumentException(ShoppingCartConstant.PRODUCT_MANDATORY);
		}
		
		if(logger.isDebugEnabled()){
			logger.debug(ShoppingCartConstant.ADDING + quantity + ShoppingCartConstant.SPACE + name);
		}

		boolean isNew = true;
		//Iterates through the existing products.
		//If same item already exist in the list then quantity is incremented.
		//Else adds as new item
		for( Product product: getProducts()) {
			if(product.getName().equals(name.toUpperCase())) {
				if(logger.isDebugEnabled()){
					logger.debug(ShoppingCartConstant.PRODUCT_EXIST + name);
				}
				product.setQuantity(product.getQuantity() + quantity);
				isNew = false;
			}
		}
		
		//If its already not exist adds as a new product
		if(isNew) {
			addToProducts(name.toUpperCase(), quantity);
						
		}
		if(logger.isDebugEnabled()){
			logger.debug(ShoppingCartConstant.NEW_LIST  + products);
		}
		
	}
	
	/**
	 * Add products to the shopping products list
	 * 
	 * @param productsList
	 */
	public void addProducts(final List<String> productsList) {
		
		if(productsList == null) {
			logger.error(ShoppingCartConstant.PRODUCT_EMPTY);
			throw new IllegalArgumentException(ShoppingCartConstant.PRODUCT_EMPTY);
		}
		
		if(logger.isDebugEnabled()){
			logger.debug(ShoppingCartConstant.ADDING  + productsList);
		}
		
		//creates a map of product and quantity 
		Map<String, Integer> productsMap = getProductsMap(productsList);

		//Iterates through the existing products.
		//If same item already exist in the list then quantity is incremented.
		//Else adds as new item
		addToExistingShoppingList(productsMap);

		if(logger.isDebugEnabled()){
			logger.debug(ShoppingCartConstant.NEW_LIST  + products);
		}
	}
	
	//Iterates through the list and creates a map of product and quantity of the same product
	private Map<String, Integer> getProductsMap(List<String> productsList){
		Map<String, Integer> productsMap = new HashMap<>();

		Stream<String> products = productsList.stream().map(String::toUpperCase);

		products.forEach(p -> {
			productsMap.compute(p, (key, value) -> value == null ? 1 : value + 1);
		});
		
		return productsMap;
	}
	
	//Iterates through the existing products.
	//If same item already exist in the list then quantity is incremented.
	//Else adds as new item
	private void addToExistingShoppingList(final Map<String, Integer> productsMap) {
		getProducts().stream().forEach(p -> productsMap.compute(p.getName(), (key, value) -> {
			if(value != null) {
				p.setQuantity(value + p.getQuantity());
			}
			productsMap.remove(p.getName());
			return value;

		} ));
		//Iterates through the remaining list which ia not already in shopping list and adds to the shopping list
		productsMap.forEach((key, value) ->  addToProducts(key, value));
	}
	
	private void addToProducts(final String name, final int quantity) {
		try {
			products.add(new Product(name, 
									 ProductEnum.valueOf(name).getCost(), 
									 quantity));
		} catch (IllegalArgumentException ex) {
			logger.error(ShoppingCartConstant.NOT_IN_LIST + name);
	    	throw new IllegalArgumentException(ShoppingCartConstant.NOT_IN_LIST + name);
	    }
	}
	
	/**
	 * Total cost of the shopping products
	 * 
	 * @return total cost of the products
	 */
	public BigDecimal totalCostOfPorducts() {
		
		if(logger.isDebugEnabled()){
			logger.debug(ShoppingCartConstant.FINAL_LIST  + products);
		}
		BigDecimal total = new BigDecimal(0.0);

		//Iterates through the products and calculates total cost of the shopping product list.
		for( Product product : getProducts()) {
			int quantity = product.getQuantity();
			total = total.add(product.getCost().multiply(new BigDecimal(quantity)));

		}
		total = total.setScale(2, RoundingMode.HALF_UP);

		if(logger.isDebugEnabled()){
			logger.debug(ShoppingCartConstant.TOTAL_COST  + products + 
					ShoppingCartConstant.IS + total);
		}
		//Scales the total to 2 decimal points
		return total;
	}
	
	/**
	 * Total cost of the shopping products after discount
	 * 
	 * @return total cost of the products
	 */
	public BigDecimal totalCostOfPorductsAfterDiscount() {
		
		if(logger.isDebugEnabled()){
			logger.debug(ShoppingCartConstant.APPLY_DISCOUNT);
		}
		DiscountService discountService = new DiscountService();
		discountService.applyDiscount(getProducts());
		BigDecimal total = totalCostOfPorducts(); 
		
		if(logger.isDebugEnabled()){
			logger.debug(ShoppingCartConstant.TOTAL_COST  + products + 
					ShoppingCartConstant.IS + total);
		}
		return total;
	}
	
	

	 
}
