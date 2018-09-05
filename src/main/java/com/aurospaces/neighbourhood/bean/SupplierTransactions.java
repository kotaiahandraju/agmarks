package com.aurospaces.neighbourhood.bean;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SupplierTransactions 
{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	protected String MasterCode,BranchCode,Category,SubCategory,Brand,ProductCode,ProductName,Variant,ProductDescription,CropsSupported,BidPrice,Quantity,Units,Status,SRD;
	
	protected Date PostedDate;
	
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
	}
	
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getSubCategory() {
		return SubCategory;
	}
	public void setSubCategory(String subCategory) {
		SubCategory = subCategory;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
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
	public void setVariant(String variant) 
	{
		Variant = variant;
	}
	
	public String getQuantity() 
	{
		return Quantity;
	}
	public void setQuantity(String quantity) 
	{
		Quantity = quantity;
	}
	
	public String getStatus() 
	{
		return Status;
	}
	public void setStatus(String status) 
	{
		Status = status;
	}
	public String getProductDescription() 
	{
		return ProductDescription;
	}
	public void setProductDescription(String productDescription) 
	{
		ProductDescription = productDescription;
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
	public String getCropsSupported() {
		return CropsSupported;
	}
	public void setCropsSupported(String cropsSupported) {
		CropsSupported = cropsSupported;
	}
	public String getBidPrice() {
		return BidPrice;
	}
	public void setBidPrice(String bidPrice) {
		BidPrice = bidPrice;
	}
	public String getUnits() {
		return Units;
	}
	public void setUnits(String units) {
		Units = units;
	}
	public String getSRD() {
		return SRD;
	}
	public void setSRD(String sRD) {
		SRD = sRD;
	}
	
	
	public Date getPostedDate() {
		return PostedDate;
	}
	public void setPostedDate(Date postedDate) {
		PostedDate = postedDate;
	}
	@Override
	public String toString() {
		return "SupplierTransactions [SNo=" + SNo + ", MasterCode=" + MasterCode + ", BranchCode=" + BranchCode
				+ ", Category=" + Category + ", SubCategory=" + SubCategory + ", Brand=" + Brand + ", ProductCode="
				+ ProductCode + ", ProductName=" + ProductName + ", Variant=" + Variant + ", ProductDescription="
				+ ProductDescription + ", CropsSupported=" + CropsSupported + ", BidPrice=" + BidPrice + ", Quantity="
				+ Quantity + ", Units=" + Units + ", Status=" + Status + ", SRD=" + SRD + ", PostedDate=" + PostedDate
				+ "]";
	}
	
	
	
}
