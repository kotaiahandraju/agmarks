package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

}
