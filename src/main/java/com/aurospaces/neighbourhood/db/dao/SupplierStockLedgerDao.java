package com.aurospaces.neighbourhood.db.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseStockLedgerDao;

@Repository(value = "supplierStockLedgerDao")
public class SupplierStockLedgerDao extends BaseStockLedgerDao {
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 
}
