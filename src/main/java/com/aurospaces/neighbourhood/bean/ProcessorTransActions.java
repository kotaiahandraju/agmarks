package com.aurospaces.neighbourhood.bean;

import java.util.Date;

public class ProcessorTransActions {
	
	
	private int S_no =0, Quantity=0;
	private String Name ,Variety,  Units, Ask_price, Comment, T_status ,Status, Token_id ;
	private Date date;
	public int getS_no() {
		return S_no;
	}
	public void setS_no(int s_no) {
		S_no = s_no;
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
	public String getAsk_price() {
		return Ask_price;
	}
	public void setAsk_price(String ask_price) {
		Ask_price = ask_price;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public String getT_status() {
		return T_status;
	}
	public void setT_status(String t_status) {
		T_status = t_status;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getToken_id() {
		return Token_id;
	}
	public void setToken_id(String token_id) {
		Token_id = token_id;
	}
	@Override
	public String toString() {
		return "ProcessorTransActions [S_no=" + S_no + ", Quantity=" + Quantity + ", Name=" + Name + ", Variety="
				+ Variety + ", Units=" + Units + ", Ask_price=" + Ask_price + ", Comment=" + Comment + ", T_status="
				+ T_status + ", Status=" + Status + ", Token_id=" + Token_id + ", date=" + date + "]";
	}
	
	
	
	

}
