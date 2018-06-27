package com.aurospaces.neighbourhood.bean;


import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author autogenerated
 */



public class FdaTrans 
{

		 /** Field mapping. **/
protected int SNo   = 0;

/** Field mapping. **/
protected String TokenId ;

/** Field mapping. **/
protected String LiveStock ;

/** Field mapping. **/
protected String Inputs ;

/** Field mapping. **/
protected String Variety ;

/** Field mapping. **/
protected String Quantity ;

/** Field mapping. **/
protected String Unit ;

/** Field mapping. **/
protected String MilkYield ;

/** Field mapping. **/
protected int Price ;

/** Field mapping. **/
protected String NearestMarket ;

/** Field mapping. **/
protected String Comment ;

/** Field mapping. **/
protected String TransactionType ;

/** Field mapping. **/
protected String Status ;

/** Field mapping. **/
protected Date Date ;


protected Date EDD; 

protected String strEDD;



public int getSNo()
{
  return SNo;
}
public void setSNo(final int SNo)
{
  this.SNo = SNo;
}
public String getTokenId()
{
  return TokenId;
}
public void setTokenId(final String TokenId)
{
  this.TokenId = TokenId;
}
public String getLiveStock()
{
  return LiveStock;
}
public void setLiveStock(final String LiveStock)
{
  this.LiveStock = LiveStock;
}
public String getInputs()
{
  return Inputs;
}
public void setInputs(final String Inputs)
{
  this.Inputs = Inputs;
}
public String getVariety()
{
  return Variety;
}
public void setVariety(final String Variety)
{
  this.Variety = Variety;
}
public String getQuantity()
{
  return Quantity;
}
public void setQuantity(final String Quantity)
{
  this.Quantity = Quantity;
}
public String getUnit()
{
  return Unit;
}
public void setUnit(final String Unit)
{
  this.Unit = Unit;
}
public String getMilkYield()
{
  return MilkYield;
}
public void setMilkYield(final String MilkYield)
{
  this.MilkYield = MilkYield;
}
public int getPrice()
{
  return Price;
}
public void setPrice(final int Price)
{
  this.Price = Price;
}
public String getNearestMarket()
{
  return NearestMarket;
}
public void setNearestMarket(final String NearestMarket)
{
  this.NearestMarket = NearestMarket;
}
public String getComment()
{
  return Comment;
}
public void setComment(final String Comment)
{
  this.Comment = Comment;
}
public String getTransactionType()
{
  return TransactionType;
}
public void setTransactionType(final String TransactionType)
{
  this.TransactionType = TransactionType;
}
public String getStatus()
{
  return Status;
}
public void setStatus(final String Status)
{
  this.Status = Status;
}
public Date getDate()
{
  return Date;
}
public void setDate(final Date Date)
{
  this.Date = Date;
}
public Date getEDD() {
	return EDD;
}
public void setEDD(Date eDD) {
	EDD = eDD;
}
public String getStrEDD() {
	return strEDD;
}
public void setStrEDD(String strEDD) {
	this.strEDD = strEDD;
}





}