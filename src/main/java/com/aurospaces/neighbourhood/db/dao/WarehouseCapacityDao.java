package com.aurospaces.neighbourhood.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseWarehouseCapacity;

@Repository(value = "warehouseCapacityDao")
public class WarehouseCapacityDao  extends BaseWarehouseCapacity{
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 


}
