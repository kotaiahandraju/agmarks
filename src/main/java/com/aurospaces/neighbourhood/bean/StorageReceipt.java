package com.aurospaces.neighbourhood.bean;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class StorageReceipt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	protected String Name,Mobile,Chamber,CsNo,BondNo,Commodity,Variety,Price,Receipts,Insurance,LoanAmt,Banks,BagsCount,Units,Status,TokenId;
protected String  strDateIn;	
	protected Date DateIn;
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
	public String getChamber() {
		return Chamber;
	}
	public void setChamber(String chamber) {
		Chamber = chamber;
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
	public String getCommodity() {
		return Commodity;
	}
	public void setCommodity(String commodity) {
		Commodity = commodity;
	}
	public String getVariety() {
		return Variety;
	}
	public void setVariety(String variety) {
		Variety = variety;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getReceipts() {
		return Receipts;
	}
	public void setReceipts(String receipts) {
		Receipts = receipts;
	}
	public String getInsurance() {
		return Insurance;
	}
	public void setInsurance(String insurance) {
		Insurance = insurance;
	}
	public String getLoanAmt() {
		return LoanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		LoanAmt = loanAmt;
	}
	public String getBanks() {
		return Banks;
	}
	public void setBanks(String banks) {
		Banks = banks;
	}
	public String getBagsCount() {
		return BagsCount;
	}
	public void setBagsCount(String bagsCount) {
		BagsCount = bagsCount;
	}
	public String getUnits() {
		return Units;
	}
	public void setUnits(String units) {
		Units = units;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getTokenId() {
		return TokenId;
	}
	public void setTokenId(String tokenId) {
		TokenId = tokenId;
	}
	public String getStrDateIn() {
		return strDateIn;
	}
	public void setStrDateIn(String strDateIn) {
		this.strDateIn = strDateIn;
	}
	public Date getDateIn() {
		return DateIn;
	}
	public void setDateIn(Date dateIn) {
		DateIn = dateIn;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}


	
	
}
