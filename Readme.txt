

This is a Shopping Cart checkout application which sells apples and oranges only.And the cost of Apple is 0.60p and orange is 0.25p. 

Below offers are applied to the shopping list.
1) Buy one get one free apples
2) Buy 3 Oranges for the cost of two oranges.

Total cost of the shopping items before discount and after discount are displayed as output.



How To Install:
---------------

1) Checkout ShoppingCart-1.0-jar-with-dependencies.jar, which is in the target folder to your local drive. 
2) Open command prompt and navigate to your local drive where ShoppingCart-1.0-jar-with-dependencies.jar is checkedout
3) Run command java -cp ShoppingCart-1.0-jar-with-dependencies.jar com.shoppingcart.ShoppingCart <input>


Eg: java -cp ShoppingCart-1.0-jar-with-dependencies.jar com.shoppingcart.ShoppingCart apple,orange,apple

Expected output:
Total cost before discount: £1.45
Total cost after discount: £0.85

Eg: java -cp ShoppingCart-1.0-jar-with-dependencies.jar com.shoppingcart.ShoppingCart apple:5,orange:5

Expected output:
Total cost before discount: £4.25
Total cost after discount: £2.80


How To Use:
-----------

There are two ways to input the shopping list.

1) By passing a comma separated list of products 
	eg: apple,orange,apple

2) By passing a comma separated list of products:quantity 
	eg:apple:5,orange:5


Description of the Module:
-------------------------

ShoppingService is the service class which has all the functionalities required to service the shopping cart.

Below are the methods exposed by ShoppingService.

1) addProduct : Takes name of the product and quantity to add to shopping list.
2) addPorducts : Takes list of products and adds to shopping list.
3) totalCostOfPorducts: Returns the total cost of the products added to shopping list.
3) totalCostOfPorductsAfterDiscount : Returns the total cost of the products after discount.


ShoppingCart is the Main calls which can be called by passing inputs.




