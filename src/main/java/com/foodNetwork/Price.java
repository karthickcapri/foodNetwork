package com.foodNetwork;

public class Price {
	int volumeDiscountCount;
	float volumeDiscountPrice;
	float unitPrice;
	
	public Price(float unitPrice) {
		this.volumeDiscountCount = 0;
		this.volumeDiscountPrice = 0;
		this.unitPrice = unitPrice;
	}
	
	public Price(int volumeDiscountCount, float volumeDiscountPrice, float unitPrice) {
		this.volumeDiscountCount = volumeDiscountCount;
		this.volumeDiscountPrice = volumeDiscountPrice;
		this.unitPrice = unitPrice;
	}
}
