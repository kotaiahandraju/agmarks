package com.aurospaces.neighbourhood.db.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.AddProduct;
import com.aurospaces.neighbourhood.bean.SupplierReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseAddProductDao;
import com.aurospaces.neighbourhood.db.basedao.BaseSupplierTransactionsDao;

@Repository(value = "supplierTransactionsdao ")
public class SupplierTransactionsdao extends BaseSupplierTransactionsDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<Map<String, Object>> getStocksRequestCounts(SupplierReg supplierReg) {
		jdbcTemplate = custom.getJdbcTemplate();
		
		String sql ="select count(*) as count from supplier_transactions where Master_code ='"+supplierReg.getMasterCode()+"' and status ='In Process' ";
System.out.println(sql);
		
		List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
		
			return retlist;
	}
	public List<Map<String, Object>> getStocksRequestDetails(SupplierReg supplierReg) {
jdbcTemplate = custom.getJdbcTemplate();
		
		String sql ="select * from supplier_transactions where Master_code ='"+supplierReg.getMasterCode()+"' and status ='In Process' ";

		
		if(StringUtils.isNotBlank(supplierReg.getBranchCode()))
		{
			sql =sql+"and Branch_code ='"+supplierReg.getBranchCode()+"'"; 
		}
		
		
		System.out.println(sql);
		
		List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
		
			return retlist;
	}
	
	

}
