package com.aurospaces.neighbourhood.bean;


import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ImportExport {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	
	protected String CompanyName ;
	protected String Ccode ;
	protected String ContactNumber ;
	protected String Address ;
	
	
	
	protected String Village ;
	protected String District ;
	protected String State ;
	protected String Pincode ;
	
	
	protected String CropName ;
	protected String Variety ;
	protected String Quantity ;
	protected String Units ;
	
	
	protected String Quality ;
	protected String Packaging ;
	protected String TransportType ;
	protected String VehicleType ;
	
	protected String ImportDate ;
	protected String ExportDate ;
	protected String Comment ;
	protected String Status ;
	
	protected String Mandal ;
	
	protected String strImportDate ;
	protected String strExportDate ;
	
	
	protected String userStatus ;
	
	
	
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getCcode() {
		return Ccode;
	}
	public void setCcode(String ccode) {
		Ccode = ccode;
	}
	public String getContactNumber() {
		return ContactNumber;
	}
	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getVillage() {
		return Village;
	}
	public void setVillage(String village) {
		Village = village;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getPincode() {
		return Pincode;
	}
	public void setPincode(String pincode) {
		Pincode = pincode;
	}
	public String getCropName() {
		return CropName;
	}
	public void setCropName(String cropName) {
		CropName = cropName;
	}
	public String getVariety() {
		return Variety;
	}
	public void setVariety(String variety) {
		Variety = variety;
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
	public String getQuality() {
		return Quality;
	}
	public void setQuality(String quality) {
		Quality = quality;
	}
	public String getPackaging() {
		return Packaging;
	}
	public void setPackaging(String packaging) {
		Packaging = packaging;
	}
	public String getTransportType() {
		return TransportType;
	}
	public void setTransportType(String transportType) {
		TransportType = transportType;
	}
	public String getVehicleType() {
		return VehicleType;
	}
	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}
	
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
	
	
	
	public String getMandal() {
		return Mandal;
	}
	public void setMandal(String mandal) {
		Mandal = mandal;
	}
	
	
	
	
	
	
	public String getImportDate() {
		return ImportDate;
	}
	public void setImportDate(String importDate) {
		ImportDate = importDate;
	}
	public String getExportDate() {
		return ExportDate;
	}
	public void setExportDate(String exportDate) {
		ExportDate = exportDate;
	}
	public String getStrImportDate() {
		return strImportDate;
	}
	public void setStrImportDate(String strImportDate) {
		this.strImportDate = strImportDate;
	}
	public String getStrExportDate() {
		return strExportDate;
	}
	public void setStrExportDate(String strExportDate) {
		this.strExportDate = strExportDate;
	}
	
	
	
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	@Override
	public String toString() {
		return "ImportExport [SNo=" + SNo + ", CompanyName=" + CompanyName + ", Ccode=" + Ccode + ", ContactNumber="
				+ ContactNumber + ", Address=" + Address + ", Village=" + Village + ", District=" + District
				+ ", State=" + State + ", Pincode=" + Pincode + ", CropName=" + CropName + ", Variety=" + Variety
				+ ", Quantity=" + Quantity + ", Units=" + Units + ", Quality=" + Quality + ", Packaging=" + Packaging
				+ ", TransportType=" + TransportType + ", VehicleType=" + VehicleType + ", Comment=" + Comment
				+ ", Status=" + Status + "]";
	}
	
	
	
	
	
	
	

}
