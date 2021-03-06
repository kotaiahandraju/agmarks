package com.aurospaces.neighbourhood.bean;


import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author autogenerated
 */



public class PlantClinic 
{

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
		 /** Field mapping. **/
protected int SNo   = 0;

/** Field mapping. **/
protected String FarmerName ;

/** Field mapping. **/
protected String Mobile ;

/** Field mapping. **/
protected String TokenId ;

/** Field mapping. **/
protected String Type ;

/** Field mapping. **/
protected String ImgName ;

/** Field mapping. **/
protected String Status ;

protected String TransactionType ;

/** Field mapping. **/
protected String Comment ,strdate,imgEncodeString;




/** Field mapping. **/
protected Date Date ;

public int getSNo()
{
  return SNo;
}
public void setSNo(final int SNo)
{
  this.SNo = SNo;
}
public String getFarmerName()
{
  return FarmerName;
}
public void setFarmerName(final String FarmerName)
{
  this.FarmerName = FarmerName;
}
public String getMobile()
{
  return Mobile;
}
public void setMobile(final String Mobile)
{
  this.Mobile = Mobile;
}
public String getTokenId()
{
  return TokenId;
}
public void setTokenId(final String TokenId)
{
  this.TokenId = TokenId;
}
public String getType()
{
  return Type;
}
public void setType(final String Type)
{
  this.Type = Type;
}
public String getImgName()
{
  return ImgName;
}
public void setImgName(final String ImgName)
{
  this.ImgName = ImgName;
}
public String getStatus()
{
  return Status;
}
public void setStatus(final String Status)
{
  this.Status = Status;
}
public String getComment()
{
  return Comment;
}
public void setComment(final String Comment)
{
  this.Comment = Comment;
}
public Date getDate()
{
  return Date;
}
public void setDate(final Date Date)
{
  this.Date = Date;
}
public String getStrdate() {
	return strdate;
}
public void setStrdate(String strdate) {
	this.strdate = strdate;
}
public String getTransactionType() {
	return TransactionType;
}
public void setTransactionType(String transactionType) {
	TransactionType = transactionType;
}
public String getImgEncodeString() {
	return imgEncodeString;
}
public void setImgEncodeString(String imgEncodeString) {
	this.imgEncodeString = imgEncodeString;
}



}