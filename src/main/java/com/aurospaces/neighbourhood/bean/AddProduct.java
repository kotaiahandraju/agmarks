package com.aurospaces.neighbourhood.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AddProduct 
{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	protected String Mobile,Category,SubCategory,Brand,ProductId,ProductCode,ProductName,Variant,CropType,Image,Quantity,Unit,Price,Status,ProductDescription;
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
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
	public void setVariant(String variant) 
	{
		Variant = variant;
	}
	public String getCropType() 
	{
		return CropType;
	}
	public void setCropType(String cropType) 
	{
		CropType = cropType;
	}
	public String getImage() 
	{
		return Image;
	}
	public void setImage(String image) 
	{
		Image = image;
	}
	public String getQuantity() 
	{
		return Quantity;
	}
	public void setQuantity(String quantity) 
	{
		Quantity = quantity;
	}
	public String getUnit() 
	{
		return Unit;
	}
	public void setUnit(String unit) 
	{
		Unit = unit;
	}
	public String getPrice() 
	{
		return Price;
	}
	public void setPrice(String price) 
	{
		Price = price;
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
	@Override
	public String toString() 
	{
		return "AddProduct [SNo=" + SNo + ", Mobile=" + Mobile + ", Category=" + Category + ", SubCategory="
				+ SubCategory + ", Brand=" + Brand + ", ProductId=" + ProductId + ", ProductCode=" + ProductCode
				+ ", ProductName=" + ProductName + ", Variant=" + Variant + ", CropType=" + CropType + ", Image="
				+ Image + ", Quantity=" + Quantity + ", Unit=" + Unit + ", Price=" + Price + ", Status=" + Status
				+ ", ProductDescription=" + ProductDescription + "]";
	}
	
	
}
