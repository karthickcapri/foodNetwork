package com.foodNetwork;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.foodNetwork.Price;
import com.foodNetwork.Service;

public class ServiceTest {
	Service s;
	
	@BeforeClass
	public void setup() {
		s = new Service();
		Price p1 = new Price(4, 7, 2);
		Price p2 = new Price(12);
		Price p3 = new Price(6, 6, 1.25f);
		Price p4 = new Price(0.15f);
		s.setPricing("A", p1);
		s.setPricing("B", p2);
		s.setPricing("C", p3);
		s.setPricing("D", p4);
	}
	
	@AfterMethod
	public void cleanCart() {
		s.getEmptyCart();
	}

		@Test	
		public void paramTest() throws Exception {
			String productItems = "ABCDABAA";
			double expectedTotal = 32.40;
			for(int i = 0; i < productItems.length(); i++) {
				s.scan(String.valueOf(productItems.charAt(i)));
			}
			double actualTotal = s.printReceipt();
			Assert.assertTrue(expectedTotal == actualTotal);
		}
		
		@Test	
		public void paramTest2() throws Exception {
			String productItems = "CCCCCCC";
			double expectedTotal = 7.25;
			for(int i = 0; i < productItems.length(); i++) {
				s.scan(String.valueOf(productItems.charAt(i)));
			}
			Assert.assertTrue(expectedTotal == s.printReceipt());
		}
		
		@Test	
		public void paramTest3() throws Exception {
			String productItems = "ABCD";
			double expectedTotal = 15.40;
			for(int i = 0; i < productItems.length(); i++) {
				s.scan(String.valueOf(productItems.charAt(i)));
			}
			Assert.assertTrue(expectedTotal == s.printReceipt());
		}
}
