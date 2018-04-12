package com.shoppingcart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import com.shoppingcart.service.ShoppingService;


/**
 * Shopping cart Main class
 * 
 * @author Asha
 *
 */
public class ShoppingCart 
{

    public static void main( String[] args )
    {
    	if(args == null || args.length ==0) {
    		throw new IllegalArgumentException("Please provide comma seperated list of products eg: apple,orange,apple or apple:5,orange:5");
    	}
    	
    	List<String> inputs = Arrays.asList(args[0].split(","));
    	ShoppingService service = new ShoppingService();
    	
    	//checks if the input is in format product:quantity
    	Map<String, Integer> productsMap = new HashMap<String, Integer>();
    	List<String> products = new ArrayList<String>();

    	inputs.stream().forEach(product -> {
    	//for(String product: inputs) {
    		if(StringUtils.isNotBlank(product) && product.contains(":")) {
    			String[] value = product.split(":");
    			if(StringUtils.isNotBlank(value[1]) && value[1].matches("\\d+")) {
    				productsMap.put(value[0], new Integer(value[1]));
    			}
    		} else {
    			products.add(product);
    		}
    	});
    	
    	
    	//If one or more inputs are in format (product1:quantity, product2:quantity) then calls addProduct method of service class
    	if(productsMap.size()>0) {
    		productsMap.forEach((key, value) -> service.addProduct(key, value));
    	} 
    	
    	//for inputs is comma separated products ( product1, product2, product1 ... ) calls addProducts method of service class
    	if(products.size()>0) {
    		service.addProducts(products);
    	}    	
    	
    	
    	System.out.println("Total cost before discount: " + (char) 339 + service.totalCostOfPorducts());
    	System.out.println("Total cost after discount: " + (char) 339 + service.totalCostOfPorductsAfterDiscount());
    	
    	
    }
    
   
}
