package com.aurospaces.neighbourhood.bean;

import java.util.Date;

public class ProcessorTransactions {
	
	
	private int Sno =0, Quantity=0;
	private String Name ,Variety,  Units, AskPrice, Comment, TStatus ,Status, TokenId ,EDD;
	private Date date;
	private Date LastUpdated;
	
	
	
	
	public int getSno() {
		return Sno;
	}
	public void setSno(int sno) {
		Sno = sno;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getVariety() {
		return Variety;
	}
	public void setVariety(String variety) {
		Variety = variety;
	}
	public String getUnits() {
		return Units;
	}
	public void setUnits(String units) {
		Units = units;
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
	
	
	public Date getLastUpdated() {
		return LastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		LastUpdated = lastUpdated;
	}
	public String getAskPrice() {
		return AskPrice;
	}
	public void setAskPrice(String askPrice) {
		AskPrice = askPrice;
	}
	public String getTStatus() {
		return TStatus;
	}
	public void setTStatus(String tStatus) {
		TStatus = tStatus;
	}
	public String getTokenId() {
		return TokenId;
	}
	public void setTokenId(String tokenId) {
		TokenId = tokenId;
	}
	
	
	
	public String getEDD() {
		return EDD;
	}
	public void setEDD(String eDD) {
		EDD = eDD;
	}
	@Override
	public String toString() {
		return "ProcessorTransactions [Sno=" + Sno + ", Quantity=" + Quantity + ", Name=" + Name + ", Variety="
				+ Variety + ", Units=" + Units + ", AskPrice=" + AskPrice + ", Comment=" + Comment + ", TStatus="
				+ TStatus + ", Status=" + Status + ", TokenId=" + TokenId + ", date=" + date + ", LastUpdated="
				+ LastUpdated + "]";
	}
	
	
	
	
	

}
