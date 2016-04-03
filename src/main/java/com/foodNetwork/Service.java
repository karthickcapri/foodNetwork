package com.foodNetwork;

import java.util.HashMap;
import java.util.Iterator;

public class Service {
	HashMap<String, Price> productMap = new HashMap<String, Price>();
	HashMap<String, Integer> cart = new HashMap<String, Integer>();
	
	public void setPricing(String product, Price price) {
		productMap.put(product, price);
	}
	
	public void getEmptyCart() {
		cart.clear();
	}
	
	public void scan(String productCode) throws Exception {
		if(productMap.containsKey(productCode)) {
			int count = 0;
			if(cart.containsKey(productCode)){
				count=cart.get(productCode);
			}
			cart.put(productCode, ++count);
		} else {
			throw new Exception("Enter a valid produt key");
		}
	}
	
	private float calculateTotal(String product, int quantity) {
		int productDiscountTot = 0;
		float productDiscountPriceTot = 0;
		int unitPricingCount = quantity;
		Price productPrice = productMap.get(product);
		if (productPrice.volumeDiscountCount != 0) {
			productDiscountTot = (int)quantity / productPrice.volumeDiscountCount;
			productDiscountPriceTot = productDiscountTot * productPrice.volumeDiscountPrice;
			unitPricingCount = quantity % productPrice.volumeDiscountCount;
		}
		float productunitPriceTot = unitPricingCount * productPrice.unitPrice;
		float total = productunitPriceTot + productDiscountPriceTot;
		return total;
	}
	
	public double printReceipt() {
		double finalTotal = 0;
		Iterator<String> it = cart.keySet().iterator();
		while(it.hasNext()) {
			String productKey = it.next();
			int quantity = cart.get(productKey);
			float productTotal = calculateTotal(productKey, quantity);
			finalTotal = finalTotal + productTotal;
			System.out.println(productKey + " - " + quantity + " - " + productTotal);
		}
		finalTotal = Math.round(finalTotal*100.0)/100.0;
		System.out.println("Final Total - "+ finalTotal);
		return finalTotal;
	}
	
	public static void main(String args[]) throws Exception {
		Service s = new Service();
		Price p1 = new Price(4, 7, 2);
		Price p2 = new Price(12);
		Price p3 = new Price(6, 6, 1.25f);
		Price p4 = new Price(0.15f);
		s.setPricing("A", p1);
		s.setPricing("B", p2);
		s.setPricing("C", p3);
		s.setPricing("D", p4);
		String productItems = "ABCDABAA";
		for(int i = 0; i < productItems.length(); i++) {
			s.scan(String.valueOf(productItems.charAt(i)));
		}
		double actualTotal = s.printReceipt();
		String productItems2 = "CCCCCCC";
		s.getEmptyCart();
		for(int i = 0; i < productItems2.length(); i++) {
			s.scan(String.valueOf(productItems2.charAt(i)));
		}
		double actualTotal2 = s.printReceipt();
		String productItems3 = "ABCDABAA";
		s.getEmptyCart();
		for(int i = 0; i < productItems3.length(); i++) {
			s.scan(String.valueOf(productItems3.charAt(i)));
		}
		//double actualTotal = s.printReceipt();
		double actualTotal3 = s.printReceipt();
		s.printReceipt();
	}
}
