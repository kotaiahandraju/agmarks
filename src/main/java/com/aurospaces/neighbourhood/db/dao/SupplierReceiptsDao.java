package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.SupplierIssues;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseSupplierReceiptDao;

@Repository(value = " supplierReceiptsDao")
public class SupplierReceiptsDao  extends BaseSupplierReceiptDao{
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<Map<String, Object>> getAnalaticsDataFor(SupplierIssues supplierIssues) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql ="select sum(Received_quantity),Receipt_date from supplier_issues where  Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"' and  Issue_date >= '"+supplierIssues.getFromdate()+"'  AND Issue_date <= '"+supplierIssues.getTodate()+"' group by Issue_date";
System.out.println(sql);
		
		List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
		
			return retlist;
	}

}
