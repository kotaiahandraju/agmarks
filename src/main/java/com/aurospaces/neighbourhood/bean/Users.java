package com.aurospaces.neighbourhood.bean;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author autogenerated
 */



public class Users 
{

		 /** Field mapping. **/
protected int UserId   = 0;

/** Field mapping. **/
protected String TokenId ;

/** Field mapping. **/
protected String Mobile ;

/** Field mapping. **/
protected String Email ;

/** Field mapping. **/
protected String User_name ;

/** Field mapping. **/
protected String Password ;

/** Field mapping. **/
protected String Status1 ;

/** Field mapping. **/
protected String Status2 ;

/** Field mapping. **/
protected String Status3 ;

/** Field mapping. **/
protected String Status4 ;

public int getUserId()
{
  return UserId;
}
public void setUserId(final int UserId)
{
  this.UserId = UserId;
}
public String getTokenId()
{
  return TokenId;
}
public void setTokenId(final String TokenId)
{
  this.TokenId = TokenId;
}
public String getMobile()
{
  return Mobile;
}
public void setMobile(final String Mobile)
{
  this.Mobile = Mobile;
}
public String getEmail()
{
  return Email;
}
public void setEmail(final String Email)
{
  this.Email = Email;
}

public String getUser_name() {
	return User_name;
}
public void setUser_name(String user_name) {
	User_name = user_name;
}
public String getPassword()
{
  return Password;
}
public void setPassword(final String Password)
{
  this.Password = Password;
}
public String getStatus1()
{
  return Status1;
}
public void setStatus1(final String Status1)
{
  this.Status1 = Status1;
}
public String getStatus2()
{
  return Status2;
}
public void setStatus2(final String Status2)
{
  this.Status2 = Status2;
}
public String getStatus3()
{
  return Status3;
}
public void setStatus3(final String Status3)
{
  this.Status3 = Status3;
}
public String getStatus4()
{
  return Status4;
}
public void setStatus4(final String Status4)
{
  this.Status4 = Status4;
}
@Override
public String toString() {
	return "Users [UserId=" + UserId + ", TokenId=" + TokenId + ", Mobile=" + Mobile + ", Email=" + Email
			+ ", User_name=" + User_name + ", Password=" + Password + ", Status1=" + Status1 + ", Status2=" + Status2
			+ ", Status3=" + Status3 + ", Status4=" + Status4 + "]";
}



}