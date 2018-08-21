package com.aurospaces.neighbourhood.bean;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SupplierIssues {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	protected Date IssueDate; 
	
	protected String strIssueDate;
	
	protected String  MasterCode,BranchCode,ConsumerName,Mobile,Address,OsNumber,InStock,Brand,ProductId,ProductCode,ProductName,Variant,IssueStock,Units,Price,dueAmount,TokenId,Status;

	
	protected String payAmount;
	
	protected String fromdate;
	
	protected String todate;
	
	public int getSNo() {
		return SNo;
	}

	public void setSNo(int sNo) {
		SNo = sNo;
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

	public String getConsumerName() {
		return ConsumerName;
	}

	public void setConsumerName(String consumerName) {
		ConsumerName = consumerName;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getOsNumber() {
		return OsNumber;
	}

	public void setOsNumber(String osNumber) {
		OsNumber = osNumber;
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

	

	public String getIssueStock() {
		return IssueStock;
	}

	public void setIssueStock(String issueStock) {
		IssueStock = issueStock;
	}

	public String getUnits() {
		return Units;
	}

	public void setUnits(String units) {
		Units = units;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	

	public String getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(String dueAmount) {
		this.dueAmount = dueAmount;
	}

	

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Date getIssueDate() {
		return IssueDate;
	}

	public void setIssueDate(Date issueDate) {
		IssueDate = issueDate;
	}
	
	

	public String getStrIssueDate() {
		return strIssueDate;
	}

	public void setStrIssueDate(String strIssueDate) {
		this.strIssueDate = strIssueDate;
	}

	public String getTokenId() {
		return TokenId;
	}

	public void setTokenId(String tokenId) {
		TokenId = tokenId;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	
	
	
	
	
	
	

}
