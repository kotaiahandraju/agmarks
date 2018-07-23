package com.aurospaces.neighbourhood.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SoilTesting {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	protected String LabType ;
	protected String LabName ;
	protected String Email ;
	protected String Mobile ;
	protected String State ;
	
	
	protected String District ;
	protected String SubDist ;
	protected String BlockName ;
	
	protected String TownVillage ;

	public int getSNo() {
		return SNo;
	}

	public void setSNo(int sNo) {
		SNo = sNo;
	}

	public String getLabType() {
		return LabType;
	}

	public void setLabType(String labType) {
		LabType = labType;
	}

	public String getLabName() {
		return LabName;
	}

	public void setLabName(String labName) {
		LabName = labName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
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

	public String getSubDist() {
		return SubDist;
	}

	public void setSubDist(String subDist) {
		SubDist = subDist;
	}

	public String getBlockName() {
		return BlockName;
	}

	public void setBlockName(String blockName) {
		BlockName = blockName;
	}

	public String getTownVillage() {
		return TownVillage;
	}

	public void setTownVillage(String townVillage) {
		TownVillage = townVillage;
	}

	@Override
	public String toString() {
		return "SoilTesting [SNo=" + SNo + ", LabType=" + LabType + ", LabName=" + LabName + ", Email=" + Email
				+ ", Mobile=" + Mobile + ", State=" + State + ", District=" + District + ", SubDist=" + SubDist
				+ ", BlockName=" + BlockName + ", TownVillage=" + TownVillage + "]";
	}
	
	
	
	
	
	
	
	
}
