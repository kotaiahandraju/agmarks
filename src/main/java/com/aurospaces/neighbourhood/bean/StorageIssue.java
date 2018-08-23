package com.aurospaces.neighbourhood.bean;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class StorageIssue {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	protected String Name,Mobile,Commodity,Issues,ChamberNo,CsNo,BondNo,OsNo,CsAdj,LoanAmt,Bank,Price,Duration,TokenId;
	
	protected Date DateOut,DateIn;
	
	protected String strDateOut,strDateIn;

	public int getSNo() {
		return SNo;
	}

	public void setSNo(int sNo) {
		SNo = sNo;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getCommodity() {
		return Commodity;
	}

	public void setCommodity(String commodity) {
		Commodity = commodity;
	}

	public String getIssues() {
		return Issues;
	}

	public void setIssues(String issues) {
		Issues = issues;
	}

	public String getChamberNo() {
		return ChamberNo;
	}

	public void setChamberNo(String chamberNo) {
		ChamberNo = chamberNo;
	}

	public String getCsNo() {
		return CsNo;
	}

	public void setCsNo(String csNo) {
		CsNo = csNo;
	}

	public String getBondNo() {
		return BondNo;
	}

	public void setBondNo(String bondNo) {
		BondNo = bondNo;
	}

	public String getOsNo() {
		return OsNo;
	}

	public void setOsNo(String osNo) {
		OsNo = osNo;
	}

	public String getCsAdj() {
		return CsAdj;
	}

	public void setCsAdj(String csAdj) {
		CsAdj = csAdj;
	}

	public String getLoanAmt() {
		return LoanAmt;
	}

	public void setLoanAmt(String loanAmt) {
		LoanAmt = loanAmt;
	}

	public String getBank() {
		return Bank;
	}

	public void setBank(String bank) {
		Bank = bank;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getDuration() {
		return Duration;
	}

	public void setDuration(String duration) {
		Duration = duration;
	}

	public String getTokenId() {
		return TokenId;
	}

	public void setTokenId(String tokenId) {
		TokenId = tokenId;
	}

	public Date getDateOut() {
		return DateOut;
	}

	public void setDateOut(Date dateOut) {
		DateOut = dateOut;
	}

	public Date getDateIn() {
		return DateIn;
	}

	public void setDateIn(Date dateIn) {
		DateIn = dateIn;
	}

	public String getStrDateOut() {
		return strDateOut;
	}

	public void setStrDateOut(String strDateOut) {
		this.strDateOut = strDateOut;
	}

	public String getStrDateIn() {
		return strDateIn;
	}

	public void setStrDateIn(String strDateIn) {
		this.strDateIn = strDateIn;
	}
	
	

	@Override
	public String toString() {
		return "StorageIssue [SNo=" + SNo + ", Name=" + Name + ", Mobile=" + Mobile + ", Commodity=" + Commodity
				+ ", Issues=" + Issues + ", ChamberNo=" + ChamberNo + ", CsNo=" + CsNo + ", BondNo=" + BondNo
				+ ", OsNo=" + OsNo + ", CsAdj=" + CsAdj + ", LoanAmt=" + LoanAmt + ", Bank=" + Bank + ", Price=" + Price
				+ ", Duration=" + Duration + ", TokenId=" + TokenId + ", DateOut=" + DateOut + ", DateIn=" + DateIn
				+ ", strDateOut=" + strDateOut + ", strDateIn=" + strDateIn + "]";
	}
	
	
	
}
