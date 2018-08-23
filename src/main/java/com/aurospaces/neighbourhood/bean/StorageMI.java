package com.aurospaces.neighbourhood.bean;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class StorageMI {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	
	protected String Name,Mobile,Commodity,Receipts,Issues,ClosingBalance,PayAmount,BillAmoun,ReBal,Insurance,LoanAmt,Bank,Status,CsNo,TokenId;
   
	protected Date DateOut,Date;
	
	protected String strDateOut,strDate;

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

	public String getReceipts() {
		return Receipts;
	}

	public void setReceipts(String receipts) {
		Receipts = receipts;
	}

	public String getIssues() {
		return Issues;
	}

	public void setIssues(String issues) {
		Issues = issues;
	}

	public String getClosingBalance() {
		return ClosingBalance;
	}

	public void setClosingBalance(String closingBalance) {
		ClosingBalance = closingBalance;
	}

	public String getPayAmount() {
		return PayAmount;
	}

	public void setPayAmount(String payAmount) {
		PayAmount = payAmount;
	}

	public String getBillAmoun() {
		return BillAmoun;
	}

	public void setBillAmoun(String billAmoun) {
		BillAmoun = billAmoun;
	}

	public String getReBal() {
		return ReBal;
	}

	public void setReBal(String reBal) {
		ReBal = reBal;
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

	public String getBank() {
		return Bank;
	}

	public void setBank(String bank) {
		Bank = bank;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getCsNo() {
		return CsNo;
	}

	public void setCsNo(String csNo) {
		CsNo = csNo;
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

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public String getStrDateOut() {
		return strDateOut;
	}

	public void setStrDateOut(String strDateOut) {
		this.strDateOut = strDateOut;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	
	
	

	
	
}
