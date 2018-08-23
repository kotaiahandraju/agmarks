package com.aurospaces.neighbourhood.db.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.StockLedger;
import com.aurospaces.neighbourhood.bean.SupplierIssues;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseSupplierissuesDao;

@Repository(value = " supplierrIssuesDao")
public class SupplierrIssuesDao extends BaseSupplierissuesDao {
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	
	
	public List<Map<String, Object>> getLatestIssues(SupplierIssues supplierIssues)
	{
	
	jdbcTemplate = custom.getJdbcTemplate();
	String sql ="";
	
	if( StringUtils.isBlank(supplierIssues.getBranchCode()))
	{
 sql = "SELECT * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"'  and Status ='in Process'";
	}
	else
	{
		sql = "SELECT * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"' and Status ='in Process'";
	}
	System.out.println(sql);
	
	List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
	
		return retlist;
	
	
	}



	public void saveDueAmount(SupplierIssues supplierIssues) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "UPDATE supplier_issues set  due_amount =?, Status = ? where S_no = ? ";
		
		jdbcTemplate.update(sql, new Object[]{supplierIssues.getDueAmount(),supplierIssues.getStatus(), supplierIssues.getSNo()});

	}
	
	
	public List<Map<String, Object>> getDueAmtList(SupplierIssues supplierIssues)
	{
	
	jdbcTemplate = custom.getJdbcTemplate();
	String sql = "SELECT * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"' and Status ='in Process' and due_amount <> '0' ";
	
	System.out.println(sql);
	
	List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
	
		return retlist;
	
	
	}
	
	
	
	public List<Map<String, Object>> getDueAmtListsenderwise(SupplierIssues supplierIssues)
	{
	
	jdbcTemplate = custom.getJdbcTemplate();
	String sql = "SELECT * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"' and Status ='in Process' and due_amount <> '0' ";
	
	System.out.println(sql);
	
	List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
	
		return retlist;
	
	
	}



	public List<Map<String, Object>> getDueAmtListSenderwise(SupplierIssues supplierIssues) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Mobile ='"+supplierIssues.getMobile()+"' ";
		
		System.out.println(sql);
		
		List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
		
			return retlist;
	}



	public List<Map<String, Object>> getProductsByIssues(SupplierIssues supplierIssues) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"' group by Product_name  ";
		System.out.println(sql);
		
		List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
		
			return retlist;
	}



	public List<Map<String, Object>> getAnalaticsData(SupplierIssues supplierIssues) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql ="select sum(Received_quantity),Receipt_date from supplier_receipts where   Product_code ='"+supplierIssues.getProductCode()+"' and  Master_code ='"+supplierIssues.getMasterCode()+"'  and  Receipt_date >= '"+supplierIssues.getFromdate()+"'  AND Receipt_date <= '"+supplierIssues.getTodate()+"' group by Receipt_date";
System.out.println(sql);
		
		List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
		
			return retlist;
	}



	public List<Map<String, Object>> getIssuesBybranchQ1(SupplierIssues supplierIssues) {
		
		
		jdbcTemplate = custom.getJdbcTemplate();
		
		int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
	    int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
	    String financiyalYearFrom="";
	    String financiyalYearTo="";
	    if(CurrentMonth<4) 
	    {
	        financiyalYearFrom= (CurrentYear-1)+"-04-01";
	        financiyalYearTo= (CurrentYear-1)+"-06-30";
	    }
	    else
	    {
	        financiyalYearFrom=(CurrentYear)+"-04-01";
	        financiyalYearTo=(CurrentYear)+"-06-31";
	    }
		
		
		String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"'   and Mobile ='"+supplierIssues.getMobile()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);
		
		List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
		
			return retlist;
	}
	
	
	
	
public List<Map<String, Object>> getIssuesBybranchQ2(SupplierIssues supplierIssues) {
		
		
		jdbcTemplate = custom.getJdbcTemplate();
		int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
	    int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
	    String financiyalYearFrom="";
	    String financiyalYearTo="";
	    if(CurrentMonth<4) 
	    {
	        financiyalYearFrom= (CurrentYear-1)+"-07-01";
	        financiyalYearTo= (CurrentYear-1)+"-09-30";
	    }
	    else
	    {
	        financiyalYearFrom=(CurrentYear)+"-07-01";
	        financiyalYearTo=(CurrentYear)+"-09-31";
	    }
		
		String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"'  and Mobile ='"+supplierIssues.getMobile()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);
		
		List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
		
			return retlist;
	
}


public List<Map<String, Object>> getIssuesBybranchQ3(SupplierIssues supplierIssues) {
	
	
	jdbcTemplate = custom.getJdbcTemplate();
	int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
    int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
    String financiyalYearFrom="";
    String financiyalYearTo="";
    if(CurrentMonth<4) 
    {
        financiyalYearFrom= (CurrentYear-1)+"-10-01";
        financiyalYearTo= (CurrentYear-1)+"-12-31";
    }
    else
    {
        financiyalYearFrom=(CurrentYear)+"-10-01";
        financiyalYearTo=(CurrentYear)+"-12-31";
    }
    
	
	
	String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"'  and Mobile ='"+supplierIssues.getMobile()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);
	
	List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
	
		return retlist;

}

public List<Map<String, Object>> getIssuesBybranchQ4(SupplierIssues supplierIssues) {
	
	
	jdbcTemplate = custom.getJdbcTemplate();
	
	
	int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
    int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
    String financiyalYearFrom="";
    String financiyalYearTo="";
    if(CurrentMonth<4) 
    {
        financiyalYearFrom= (CurrentYear)+"-01-01";
        financiyalYearTo= (CurrentYear)+"-03-31";
    }
    else
    {
        financiyalYearFrom=(CurrentYear+1)+"-01-01";
        financiyalYearTo=(CurrentYear+1)+"-03-31";
    }
    
    
	
	
	
	
	String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"'  and Mobile ='"+supplierIssues.getMobile()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);
	
	List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
	
		return retlist;

}



public List<Map<String, Object>> getIssuesByBranchH1(SupplierIssues supplierIssues) {
jdbcTemplate = custom.getJdbcTemplate();
	
	
	int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
    int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
    String financiyalYearFrom="";
    String financiyalYearTo="";
    if(CurrentMonth<4) 
    {
        financiyalYearFrom= (CurrentYear-1)+"-04-01";
        financiyalYearTo= (CurrentYear-1)+"-09-30";
   
    }
    else
    {
    	 financiyalYearFrom= (CurrentYear)+"-04-01";
         financiyalYearTo= (CurrentYear)+"-09-30";
    	
    }
	
	
	
	
	String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"'  and Mobile ='"+supplierIssues.getMobile()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);
	
	List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
	
		return retlist;

}


public List<Map<String, Object>> getIssuesByBranchH2(SupplierIssues supplierIssues) {
jdbcTemplate = custom.getJdbcTemplate();
	
	
	int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
    int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
    String financiyalYearFrom="";
    String financiyalYearTo="";
    if(CurrentMonth<4) 
    {
        financiyalYearFrom= (CurrentYear-1)+"-09-01";
        financiyalYearTo= (CurrentYear)+"-03-31";
    
    }
    else
    {
    	financiyalYearFrom= (CurrentYear)+"-09-01";
        financiyalYearTo= (CurrentYear+1)+"-03-31";
    	
    }
    
	
	
	
	
	String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"'  and Mobile ='"+supplierIssues.getMobile()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);
	
	List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
	
		return retlist;

}



public List<Map<String, Object>> getIssuesByBranchYearly(SupplierIssues supplierIssues) {
jdbcTemplate = custom.getJdbcTemplate();
	
	
	int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
    int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
    String financiyalYearFrom="";
    String financiyalYearTo="";
    if(CurrentMonth<4) 
    {
        financiyalYearFrom= (CurrentYear-1)+"-04-01";
        financiyalYearTo= (CurrentYear)+"-03-31";
    
    }
    
	else
	{
		 financiyalYearFrom= (CurrentYear)+"-04-01";
	        financiyalYearTo= (CurrentYear+1)+"-03-31";
	}
	
	
	
	String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"'  and Mobile ='"+supplierIssues.getMobile()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);
	
	List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
	
		return retlist;
}











public List<Map<String, Object>> getIssuesByProductQ1(SupplierIssues supplierIssues) {
	
	
	jdbcTemplate = custom.getJdbcTemplate();
	
	int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
    int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
    String financiyalYearFrom="";
    String financiyalYearTo="";
    if(CurrentMonth<4) 
    {
        financiyalYearFrom= (CurrentYear-1)+"-04-01";
        financiyalYearTo= (CurrentYear-1)+"-06-30";
    }
    else
    {
        financiyalYearFrom=(CurrentYear)+"-04-01";
        financiyalYearTo=(CurrentYear)+"-06-31";
    }
	
	
	String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Product_code ='"+supplierIssues.getProductCode()+"'   and Product_name ='"+supplierIssues.getProductName()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);
	
	List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
	
		return retlist;
}




public List<Map<String, Object>> getIssuesByProductQ2(SupplierIssues supplierIssues) {
	
	
	jdbcTemplate = custom.getJdbcTemplate();
	int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
    int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
    String financiyalYearFrom="";
    String financiyalYearTo="";
    if(CurrentMonth<4) 
    {
        financiyalYearFrom= (CurrentYear-1)+"-07-01";
        financiyalYearTo= (CurrentYear-1)+"-09-30";
    }
    else
    {
        financiyalYearFrom=(CurrentYear)+"-01-01";
        financiyalYearTo=(CurrentYear)+"-03-31";
    }
	
	String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Product_code ='"+supplierIssues.getProductCode()+"'  and Product_name ='"+supplierIssues.getProductName()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);
	
	List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
	
		return retlist;

}


public List<Map<String, Object>> getIssuesByProductQ3(SupplierIssues supplierIssues) {


jdbcTemplate = custom.getJdbcTemplate();
int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
String financiyalYearFrom="";
String financiyalYearTo="";
if(CurrentMonth<4) 
{
    financiyalYearFrom= (CurrentYear-1)+"-10-01";
    financiyalYearTo= (CurrentYear-1)+"-12-31";
}
else
{
    financiyalYearFrom=(CurrentYear)+"-10-01";
    financiyalYearTo=(CurrentYear)+"-12-31";
}



String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Product_code ='"+supplierIssues.getProductCode()+"'  and Product_name ='"+supplierIssues.getProductName()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);

List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);

	return retlist;

}

public List<Map<String, Object>> getIssuesByProductQ4(SupplierIssues supplierIssues) {


jdbcTemplate = custom.getJdbcTemplate();


int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
String financiyalYearFrom="";
String financiyalYearTo="";
if(CurrentMonth<4) 
{
    financiyalYearFrom= (CurrentYear)+"-01-01";
    financiyalYearTo= (CurrentYear)+"-03-31";
}
else
{
    financiyalYearFrom=(CurrentYear+1)+"-01-01";
    financiyalYearTo=(CurrentYear+1)+"-03-31";
}






String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Product_code ='"+supplierIssues.getProductCode()+"'  and Product_name ='"+supplierIssues.getProductName()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);

List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);

	return retlist;

}



public List<Map<String, Object>> getIssuesByProductH1(SupplierIssues supplierIssues) {
jdbcTemplate = custom.getJdbcTemplate();


int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
String financiyalYearFrom="";
String financiyalYearTo="";
if(CurrentMonth<4) 
{
    financiyalYearFrom= (CurrentYear-1)+"-04-01";
    financiyalYearTo= (CurrentYear-1)+"-09-30";

}
else
{
	 financiyalYearFrom= (CurrentYear)+"-04-01";
     financiyalYearTo= (CurrentYear)+"-09-30";
	
}




String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Product_code ='"+supplierIssues.getProductCode()+"'  and Product_name ='"+supplierIssues.getProductName()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);

List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);

	return retlist;

}


public List<Map<String, Object>> getIssuesByProductH2(SupplierIssues supplierIssues) {
jdbcTemplate = custom.getJdbcTemplate();


int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
String financiyalYearFrom="";
String financiyalYearTo="";
if(CurrentMonth<4) 
{
    financiyalYearFrom= (CurrentYear-1)+"-09-01";
    financiyalYearTo= (CurrentYear)+"-03-31";

}
else
{
	financiyalYearFrom= (CurrentYear)+"-09-01";
    financiyalYearTo= (CurrentYear+1)+"-03-31";
	
}





String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Product_code ='"+supplierIssues.getProductCode()+"'  and Product_name ='"+supplierIssues.getProductName()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);

List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);

	return retlist;

}



public List<Map<String, Object>> getIssuesByProductYearly(SupplierIssues supplierIssues) {
jdbcTemplate = custom.getJdbcTemplate();


int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
String financiyalYearFrom="";
String financiyalYearTo="";
if(CurrentMonth<4) 
{
    financiyalYearFrom= (CurrentYear-1)+"-04-01";
    financiyalYearTo= (CurrentYear)+"-03-31";

}

else
{
	 financiyalYearFrom= (CurrentYear)+"-04-01";
        financiyalYearTo= (CurrentYear+1)+"-03-31";
}



String sql ="select * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Product_code ='"+supplierIssues.getProductCode()+"'  and Product_name ='"+supplierIssues.getProductName()+"' and  Issue_date >= '"+financiyalYearFrom+"'  AND Issue_date <= '"+financiyalYearTo+"'";
System.out.println(sql);

List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);

	return retlist;
}



public List<Map<String, Object>> getClosingBalOfProduct(StockLedger stockLedger) {
	jdbcTemplate = custom.getJdbcTemplate();
	//String sql = "SELECT * from stock_ledger where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"' ";
	String sql = "SELECT * from stock_ledger where  Master_code ='"+stockLedger.getMasterCode()+"' and Product_code ='"+stockLedger.getProductCode()+"' order by S_no desc limit 1 ";
	
	
	System.out.println(sql);
	
	List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
	
		return retlist;
}























}
