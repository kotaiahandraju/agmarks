package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.AnalyticsPojo;
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
	String sql = "SELECT * from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"' and Status ='in Process'";
	
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
		String sql = "SELECT * from supplier_issues where  Mobile ='"+supplierIssues.getMobile()+"' ";
		
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
		String sql ="select sum(Received_quantity),Receipt_date from supplier_receipts where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"' and  Receipt_date >= '"+supplierIssues.getFromdate()+"'  AND Receipt_date <= '"+supplierIssues.getTodate()+"' group by Receipt_date";
System.out.println(sql);
		
		List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
		
			return retlist;
	}


}
