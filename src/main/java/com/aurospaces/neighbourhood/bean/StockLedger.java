package com.aurospaces.neighbourhood.bean;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class StockLedger {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	protected String MasterCode,Mobile,BranchCode,ProductCode,OpeningBalance,Receipts,Issues,ClosingBalance,PayAmount,BillAmoun,ReBal,TokenId;
	protected Date date;
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
	}
	public String getMasterCode() {
		return MasterCode;
	}
	public void setMasterCode(String masterCode) {
		MasterCode = masterCode;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getBranchCode() {
		return BranchCode;
	}
	public void setBranchCode(String branchCode) {
		BranchCode = branchCode;
	}
	public String getProductCode() {
		return ProductCode;
	}
	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}
	public String getOpeningBalance() {
		return OpeningBalance;
	}
	public void setOpeningBalance(String openingBalance) {
		OpeningBalance = openingBalance;
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
	public String getTokenId() {
		return TokenId;
	}
	public void setTokenId(String tokenId) {
		TokenId = tokenId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "StockLedger [SNo=" + SNo + ", MasterCode=" + MasterCode + ", Mobile=" + Mobile + ", BranchCode="
				+ BranchCode + ", ProductCode=" + ProductCode + ", OpeningBalance=" + OpeningBalance + ", Receipts="
				+ Receipts + ", Issues=" + Issues + ", ClosingBalance=" + ClosingBalance + ", PayAmount=" + PayAmount
				+ ", BillAmoun=" + BillAmoun + ", ReBal=" + ReBal + ", TokenId=" + TokenId + ", date=" + date + "]";
	}
	
	
	
}
