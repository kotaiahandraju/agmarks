package com.aurospaces.neighbourhood.bean;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SupplierPA {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	protected Date Tdate; 
	
	
	protected String  MasterCode,BranchCode,BranchName,ConsumerName,InchargeMobile,Address,InStock,Brand,ProductId,ProductCode,ProductName,Variant,Receipts,Issues,Units,TotalPrice,TokenId,Status;
	
	protected String strtdate;

	public int getSNo() {
		return SNo;
	}

	public void setSNo(int sNo) {
		SNo = sNo;
	}

	public Date getTdate() {
		return Tdate;
	}

	public void setTdate(Date tdate) {
		Tdate = tdate;
	}

	public String getMasterCode() {
		return MasterCode;
	}

	public void setMasterCode(String masterCode) {
		MasterCode = masterCode;
	}

	public String getBranchCode() {
		return BranchCode;
	}

	public void setBranchCode(String branchCode) {
		BranchCode = branchCode;
	}

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}

	public String getConsumerName() {
		return ConsumerName;
	}

	public void setConsumerName(String consumerName) {
		ConsumerName = consumerName;
	}

	public String getInchargeMobile() {
		return InchargeMobile;
	}

	public void setInchargeMobile(String inchargeMobile) {
		InchargeMobile = inchargeMobile;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getInStock() {
		return InStock;
	}

	public void setInStock(String inStock) {
		InStock = inStock;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String productId) {
		ProductId = productId;
	}

	public String getProductCode() {
		return ProductCode;
	}

	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getVariant() {
		return Variant;
	}

	public void setVariant(String variant) {
		Variant = variant;
	}

	public String getReceipts() {
		return Receipts;
	}

	public void setReceipts(String receipts) {
		Receipts = receipts;
	}

	public String getIssues() {
		return Issues;
	}

	public void setIssues(String issues) {
		Issues = issues;
	}

	public String getUnits() {
		return Units;
	}

	public void setUnits(String units) {
		Units = units;
	}

	public String getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}

	public String getTokenId() {
		return TokenId;
	}

	public void setTokenId(String tokenId) {
		TokenId = tokenId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getStrtdate() {
		return strtdate;
	}

	public void setStrtdate(String strtdate) {
		this.strtdate = strtdate;
	}

	@Override
	public String toString() {
		return "SupplierPA [SNo=" + SNo + ", Tdate=" + Tdate + ", MasterCode=" + MasterCode + ", BranchCode="
				+ BranchCode + ", BranchName=" + BranchName + ", ConsumerName=" + ConsumerName + ", InchargeMobile="
				+ InchargeMobile + ", Address=" + Address + ", InStock=" + InStock + ", Brand=" + Brand + ", ProductId="
				+ ProductId + ", ProductCode=" + ProductCode + ", ProductName=" + ProductName + ", Variant=" + Variant
				+ ", Receipts=" + Receipts + ", Issues=" + Issues + ", Units=" + Units + ", TotalPrice=" + TotalPrice
				+ ", TokenId=" + TokenId + ", Status=" + Status + ", strtdate=" + strtdate + "]";
	}
	
	
	

}
