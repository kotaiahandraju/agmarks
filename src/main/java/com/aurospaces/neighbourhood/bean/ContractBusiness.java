package com.aurospaces.neighbourhood.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ContractBusiness {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo = 0;
	
	protected String CompanyName ;
	protected String Ccode ;
	protected String Mobile ;
	protected String GST ;
	protected String CompanyId ;
	
	
	protected String AreaIntContract ;
	protected String RequiredCrops ;
	protected String CultivableLand ;
	protected String Comment ;
	protected String Status;
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
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
	public String getGST() {
		return GST;
	}
	public void setGST(String gST) {
		GST = gST;
	}
	public String getCompanyId() {
		return CompanyId;
	}
	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}
	public String getAreaIntContract() {
		return AreaIntContract;
	}
	public void setAreaIntContract(String areaIntContract) {
		AreaIntContract = areaIntContract;
	}
	public String getRequiredCrops() {
		return RequiredCrops;
	}
	public void setRequiredCrops(String requiredCrops) {
		RequiredCrops = requiredCrops;
	}
	public String getCultivableLand() {
		return CultivableLand;
	}
	public void setCultivableLand(String cultivableLand) {
		CultivableLand = cultivableLand;
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
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	
	
	@Override
	public String toString() {
		return "ContractBusiness [SNo=" + SNo + ", CompanyName=" + CompanyName + ", Ccode=" + Ccode + ", Mobile="
				+ Mobile + ", GST=" + GST + ", CompanyId=" + CompanyId + ", AreaIntContract=" + AreaIntContract
				+ ", RequiredCrops=" + RequiredCrops + ", CultivableLand=" + CultivableLand + ", Comment=" + Comment
				+ ", Status=" + Status + "]";
	}
	
	
	
}
