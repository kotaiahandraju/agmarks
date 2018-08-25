package com.aurospaces.neighbourhood.bean;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class WarehouseCapacity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	
	protected  String TokenId,TotalCapacity,Occupancy,AvailCapacity,AskPrice;
	protected Date date;
	
	protected String strdate;
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
	}
	public String getTokenId() {
		return TokenId;
	}
	public void setTokenId(String tokenId) {
		TokenId = tokenId;
	}
	public String getTotalCapacity() {
		return TotalCapacity;
	}
	public void setTotalCapacity(String totalCapacity) {
		TotalCapacity = totalCapacity;
	}
	public String getOccupancy() {
		return Occupancy;
	}
	public void setOccupancy(String occupancy) {
		Occupancy = occupancy;
	}
	public String getAvailCapacity() {
		return AvailCapacity;
	}
	public void setAvailCapacity(String availCapacity) {
		AvailCapacity = availCapacity;
	}
	public String getAskPrice() {
		return AskPrice;
	}
	public void setAskPrice(String askPrice) {
		AskPrice = askPrice;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	public String getStrdate() {
		return strdate;
	}
	public void setStrdate(String strdate) {
		this.strdate = strdate;
	}
	@Override
	public String toString() {
		return "WarehouseCapacity [SNo=" + SNo + ", TokenId=" + TokenId + ", TotalCapacity=" + TotalCapacity
				+ ", Occupancy=" + Occupancy + ", AvailCapacity=" + AvailCapacity + ", AskPrice=" + AskPrice + ", date="
				+ date + "]";
	}
	
	
	

}
