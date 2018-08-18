package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.StockLedger;
import com.aurospaces.neighbourhood.bean.SupplierReceipt;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseStockLedgerDao;

@Repository(value = "supplierStockLedgerDao")
public class SupplierStockLedgerDao extends BaseStockLedgerDao {
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<StockLedger> getLedgerByProductCode(SupplierReceipt supplierReceipt) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from stock_ledger where SNo = ? ";
		List<StockLedger> retlist = jdbcTemplate.query(sql,new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(StockLedger.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	} 
}
