package com.aurospaces.neighbourhood.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SeedTesting {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	
	protected String State ;
	protected String District ;
	protected String LabName ;
	protected String MachinePower ;
	protected String Address ;
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public String getLabName() {
		return LabName;
	}
	public void setLabName(String labName) {
		LabName = labName;
	}
	public String getMachinePower() {
		return MachinePower;
	}
	public void setMachinePower(String machinePower) {
		MachinePower = machinePower;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	@Override
	public String toString() {
		return "SeedTesting [SNo=" + SNo + ", State=" + State + ", District=" + District + ", LabName=" + LabName
				+ ", MachinePower=" + MachinePower + ", Address=" + Address + "]";
	}
	
	
	
	

}
