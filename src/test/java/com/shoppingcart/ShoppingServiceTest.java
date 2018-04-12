package com.shoppingcart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.shoppingcart.data.Product;
import com.shoppingcart.service.Shopping;
import com.shoppingcart.service.ShoppingService;

public class ShoppingServiceTest {
	private Shopping service = new ShoppingService();
	
	@Rule
	public ExpectedException thrown= ExpectedException.none();

	
	/**
	 * Set empty product list
	 */
	@Before
	public void setUp() {
		List<Product> products = new ArrayList<Product>();
		service.setProducts(products);
	}
	
	/**
	 * Clears the product list
	 */
	@After
	public void cleanUp() {
		service.setProducts(null);
	}

	
	/**
	 * Test if the name is null then returns Illegal exception
	 */
	@Test
	public void testProductNameNull() {		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Product name is required");
		service.addProduct(null, 0);
	}
	
	/**
	 * Test if the product is not available then returns Illegal exception
	 * addProduct method
	 */
	@Test 
	public void testProductNameNotAvailable1() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Product not in shopping list: ");
		service.addProduct("Test", 1);	

	}
	
	/**
	 * Test if the product is not available then returns Illegal exception
	 * addProduts method
	 */
	@Test 
	public void testProductNameNotAvailable2() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Product not in shopping list: ");
		List<String> values = new ArrayList<String>();
		values.add("Test");
		service.addProducts(values);	

	}
	
	
	/**
	 * Tests Add Apple
	 */
	@Test
	public void testAddApple() {

		service.addProduct("APPLE", 1);
		Assert.assertEquals(new BigDecimal(0.60).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	/**
	 * Tests Add Apples
	 */
	@Test
	public void testAddApples() {

		service.addProduct("APPLE", 4);
		Assert.assertEquals(new BigDecimal(2.40).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	/**
	 * Tests Add orange
	 */
	@Test
	public void testAddOrange() {

		service.addProduct("ORANGE", 1);
		Assert.assertEquals(new BigDecimal(0.25).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	
	/**
	 * Tests Add oranges
	 */
	@Test
	public void testAddOranges() {

		service.addProduct("ORANGE", 4);
		Assert.assertEquals(new BigDecimal(1.00).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	
	/**
	 * Tests Add apples and oranges test1
	 */
	@Test
	public void testAddApplesAndOranges1() {

		service.addProduct("APPLE", 4);
		service.addProduct("ORANGE", 4);
		Assert.assertEquals(new BigDecimal(3.40).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	/**
	 * Tests Add apples and oranges test2
	 */
	@Test
	public void testAddApplesAndOranges2() {

		service.addProduct("APPLE", 7);
		service.addProduct("ORANGE", 10);
		Assert.assertEquals(new BigDecimal(6.70).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	
	/**
	 * Tests Add apples and oranges test3
	 */
	@Test
	public void testAddApplesAndOranges3() {

		service.addProduct("APPLE", 3);
		service.addProduct("ORANGE", 1);
		Assert.assertEquals(new BigDecimal(2.05).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	/**
	 * Tests Add apples and oranges test1
	 */
	@Test
	public void testAddListOfApplesAndOranges1() {

		List<String> products = new ArrayList<String>();
		addItemsToList("Apple", 4, products);
		addItemsToList("Orange", 4, products);
		service.addProducts(products);
		Assert.assertEquals(new BigDecimal(3.40).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	/**
	 * Tests Add apples and oranges test2
	 */
	@Test
	public void testAddListOfApplesAndOranges2() {

		List<String> products = new ArrayList<String>();
		addItemsToList("Apple", 7, products);
		addItemsToList("Orange", 10, products);
		service.addProducts(products);
		Assert.assertEquals(new BigDecimal(6.70).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	/**
	 * Tests Add apples and oranges test3
	 */
	@Test
	public void testAddListOfApplesAndOranges3() {

		List<String> products = new ArrayList<String>();
		addItemsToList("Apple", 3, products);
		addItemsToList("Orange", 1, products);
		service.addProducts(products);
		Assert.assertEquals(new BigDecimal(2.05).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	
	/**
	 * Tests addProduct with existing product list test1
	 * 
	 */
	@Test
	public void testAddApplesAndOrangesWithExistingProducts1() {

		List<String> products = new ArrayList<String>();
		addItemsToList("Apple", 3, products);
		addItemsToList("Orange", 1, products);
		service.addProducts(products);
		
		service.addProduct("APPLE", 3);
		service.addProduct("ORANGE", 1);
		
		Assert.assertEquals(new BigDecimal(4.10).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	/**
	 * Tests addProduct with existing product list test2
	 * 
	 * tests with existing product list but not the product trying to add
	 */
	@Test
	public void testAddApplesAndOrangesWithExistingProducts2() {

		List<String> products = new ArrayList<String>();
		addItemsToList("Apple", 3, products);
		service.addProducts(products);
		
		service.addProduct("ORANGE", 1);
		
		Assert.assertEquals(new BigDecimal(2.05).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	/**
	 * Tests addProducts with existing product list test1
	 * 
	 * 
	 */
	@Test
	public void testAddListApplesAndOrangesWithExistingProducts1() {
		
		service.addProduct("ORANGE", 4);
		service.addProduct("Apple", 2);
		
		List<String> products = new ArrayList<String>();
		addItemsToList("Apple", 3, products);
		addItemsToList("orange", 2, products);
		service.addProducts(products);
		
		Assert.assertEquals(new BigDecimal(4.50).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	/**
	 * Tests addProducts with existing product list test2
	 * 
	 * tests with existing product list but not the product trying to add
	 */
	@Test
	public void testAddListApplesAndOrangesWithExistingProducts2() {
		
		service.addProduct("ORANGE", 4);
		
		List<String> products = new ArrayList<String>();
		addItemsToList("Apple", 3, products);
		service.addProducts(products);
		
		Assert.assertEquals(new BigDecimal(2.80).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
	}
	
	/**
	 * Tests discount test1
	 * 
	 * 
	 */
	@Test
	public void testAddApplessAndOrangesDiscount1() {
		
		service.addProduct("ORANGE", 3);
		
		List<String> products = new ArrayList<String>();
		addItemsToList("Apple", 2, products);
		service.addProducts(products);
		
		Assert.assertEquals(new BigDecimal(1.10).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorductsAfterDiscount());
	}

	
	/**
	 * Tests discount test2
	 * 
	 */
	@Test
	public void testAddApplessAndOrangesDiscount2() {
		
		service.addProduct("ORANGE", 10);
		
		List<String> products = new ArrayList<String>();
		addItemsToList("Apple", 5, products);
		service.addProducts(products);
		Assert.assertEquals(new BigDecimal(5.50).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
		Assert.assertEquals(new BigDecimal(3.55).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorductsAfterDiscount());
	}
	
	/**
	 * Tests discount test3
	 * 
	 */
	@Test
	public void testAddApplessAndOrangesDiscount3() {
		
		List<String> products = new ArrayList<String>();
		addItemsToList("Apple", 5, products);
		service.addProducts(products);
		
		service.addProduct("ORANGE", 10);
			
		Assert.assertEquals(new BigDecimal(5.50).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorducts());
		Assert.assertEquals(new BigDecimal(3.55).setScale(2, RoundingMode.HALF_UP), service.totalCostOfPorductsAfterDiscount());
	}
	
	private void addItemsToList(String name, int count, List<String> list) {
		for(int i =0; i<count; i++) {
			list.add(name);
		}
	}
	
}
