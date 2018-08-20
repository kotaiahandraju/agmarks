package com.aurospaces.neighbourhood.db.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.aurospaces.neighbourhood.bean.StockLedger;
import com.aurospaces.neighbourhood.bean.SupplierIssues;
import com.aurospaces.neighbourhood.bean.SupplierReceipt;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseStockLedgerDao;

import freemarker.template.utility.StringUtil;

@Repository(value = "supplierStockLedgerDao")
public class SupplierStockLedgerDao extends BaseStockLedgerDao {
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<StockLedger> getLedgerByProductCode(SupplierReceipt supplierReceipt) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from stock_ledger where  Product_code  = '"+supplierReceipt.getProductCode()+" ' and Master_code ='"+supplierReceipt.getMasterCode()+"' ";
		
	
		List<StockLedger> retlist = jdbcTemplate.query(sql,new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(StockLedger.class));
		if(retlist.size() > 0)
			return retlist;
		return Collections.emptyList();
	}
	public List<StockLedger> getLedgerByProductCodeforIssues(SupplierIssues supplierIssues) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from stock_ledger where  Product_code  = '"+supplierIssues.getProductCode()+" 'and Master_code ='"+supplierIssues.getMasterCode()+"' and  Branch_code ='"+supplierIssues.getBranchCode()+"'";
		
		System.out.println(sql);
		
		List<StockLedger> retlist = jdbcTemplate.query(sql,new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(StockLedger.class));
		if(retlist.size() > 0)
			return retlist;
		return Collections.emptyList();
	}
	public List<Map<String, Object>> getLedgerData() {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql="select * from stock_ledger  order by S_no desc";
		List<Map<String, Object>> retlist =jdbcTemplate.queryForList(hql);
		return retlist;
	
	} 
}
