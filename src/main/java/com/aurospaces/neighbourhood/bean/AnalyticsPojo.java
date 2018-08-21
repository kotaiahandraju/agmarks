package com.aurospaces.neighbourhood.bean;

public class AnalyticsPojo {
	
	private String productName;
	
	private String fromDate;
	
	private String toDate;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "AnalyticsPojo [productName=" + productName + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
	
	
	
	
	

}
