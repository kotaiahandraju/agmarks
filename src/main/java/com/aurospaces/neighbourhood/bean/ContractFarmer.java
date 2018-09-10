package com.aurospaces.neighbourhood.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ContractFarmer {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	protected String FarmerName ;
	protected String Ccode ;
	
	
	protected String Mobile ;
	protected String Address ;
	protected String Village ;
	protected String District ;
	protected String State ;
	
	
	protected String Pincode ;
	protected String CultivableLand ;
	protected String Place ;
	protected String CropToGrown ;
	protected String Comments ;
	protected String Status ;
	
	protected String Mandal;
	
	
	protected String Other ;
	
	protected String Units;
	protected String UserStatus;
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
	}
	public String getFarmerName() {
		return FarmerName;
	}
	public void setFarmerName(String farmerName) {
		FarmerName = farmerName;
	}
	public String getCcode() {
		return Ccode;
	}
	public void setCcode(String ccode) {
		Ccode = ccode;
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
	public String getCultivableLand() {
		return CultivableLand;
	}
	public void setCultivableLand(String cultivableLand) {
		CultivableLand = cultivableLand;
	}
	public String getPlace() {
		return Place;
	}
	public void setPlace(String place) {
		Place = place;
	}
	public String getCropToGrown() {
		return CropToGrown;
	}
	public void setCropToGrown(String cropToGrown) {
		CropToGrown = cropToGrown;
	}
	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
	
	public String getOther() {
		return Other;
	}
	public void setOther(String other) {
		Other = other;
	}
	
	
	
	public String getMandal() {
		return Mandal;
	}
	public void setMandal(String mandal) {
		Mandal = mandal;
	}
	
	
	
	public String getUnits() {
		return Units;
	}
	public void setUnits(String units) {
		Units = units;
	}
	public String getUserStatus() {
		return UserStatus;
	}
	public void setUserStatus(String userStatus) {
		UserStatus = userStatus;
	}
	@Override
	public String toString() {
		return "ContractFarmer [SNo=" + SNo + ", FarmerName=" + FarmerName + ", Ccode=" + Ccode + ", Mobile=" + Mobile
				+ ", Address=" + Address + ", Village=" + Village + ", District=" + District + ", State=" + State
				+ ", Pincode=" + Pincode + ", CultivableLand=" + CultivableLand + ", Place=" + Place + ", CropToGrown="
				+ CropToGrown + ", Comments=" + Comments + ", Status=" + Status + ", Mandal=" + Mandal + ", Other="
				+ Other + "]";
	}
	
	
	
	
	
}
