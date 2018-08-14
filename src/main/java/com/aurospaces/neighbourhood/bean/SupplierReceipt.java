package com.aurospaces.neighbourhood.bean;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SupplierReceipt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	protected String MasterCode, ReceivedFrom,Mobile,ReceiptNo,Brand,ProductId,ProductCode,ProductName, Variant,Quantity,Units,ReceivedQuantity,Runits,Price, TokenId,Status;
	protected Date ReceiptDate;
	
	protected String strReceiptDate;
	
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
	public String getReceivedFrom() {
		return ReceivedFrom;
	}
	public void setReceivedFrom(String receivedFrom) {
		ReceivedFrom = receivedFrom;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getReceiptNo() {
		return ReceiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		ReceiptNo = receiptNo;
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
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getUnits() {
		return Units;
	}
	public void setUnits(String units) {
		Units = units;
	}
	public String getReceivedQuantity() {
		return ReceivedQuantity;
	}
	public void setReceivedQuantity(String receivedQuantity) {
		ReceivedQuantity = receivedQuantity;
	}
	public String getRunits() {
		return Runits;
	}
	public void setRunits(String runits) {
		Runits = runits;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
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
	public Date getReceiptDate() {
		return ReceiptDate;
	}
	public void setReceiptDate(Date receiptDate) {
		ReceiptDate = receiptDate;
	}
	
	
	public String getStrReceiptDate() {
		return strReceiptDate;
	}
	public void setStrReceiptDate(String strReceiptDate) {
		this.strReceiptDate = strReceiptDate;
	}
	@Override
	public String toString() {
		return "SupplierReceipt [SNo=" + SNo + ", MasterCode=" + MasterCode + ", ReceivedFrom=" + ReceivedFrom
				+ ", Mobile=" + Mobile + ", ReceiptNo=" + ReceiptNo + ", Brand=" + Brand + ", ProductId=" + ProductId
				+ ", ProductCode=" + ProductCode + ", ProductName=" + ProductName + ", Variant=" + Variant
				+ ", Quantity=" + Quantity + ", Units=" + Units + ", ReceivedQuantity=" + ReceivedQuantity + ", Runits="
				+ Runits + ", Price=" + Price + ", TokenId=" + TokenId + ", Status=" + Status + ", ReceiptDate="
				+ ReceiptDate + "]";
	}
	
	
	
	

	
	

}
