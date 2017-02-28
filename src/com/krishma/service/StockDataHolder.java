package com.krishma.service;

public class StockDataHolder {
	
	private String companyName;

	private Double lastValueInDollar;
	
	private Double previousValueInDollar;

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Double getLastValueInDollar() {
		return lastValueInDollar;
	}
	public void setLastValueInDollar(Double lastValueInDollar) {
		this.lastValueInDollar = lastValueInDollar;
	}
	public Double getPreviousValueInDollar() {
		return previousValueInDollar;
	}
	public void setPreviousValueInDollar(Double previousValueInDollar) {
		this.previousValueInDollar = previousValueInDollar;
	}
	
}
