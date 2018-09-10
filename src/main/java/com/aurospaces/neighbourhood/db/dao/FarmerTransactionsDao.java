
package com.aurospaces.neighbourhood.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.FarmerTransactions;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseFarmerTransactionsDao;




@Repository(value = "farmerTransactionsDao")
public class FarmerTransactionsDao extends BaseFarmerTransactionsDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public void editFarmerTransactions(FarmerTransactions farmerTransactions) {
		jdbcTemplate = custom.getJdbcTemplate();
		
		java.sql.Timestamp EDD = 
				new java.sql.Timestamp(farmerTransactions.getEDD().getTime()); 
		
		String sql = "UPDATE farmer_transactions  set  Quantity = ?,Units = ? ,EDD =?  where S_no = ? ";
		
		jdbcTemplate.update(sql, new Object[]{farmerTransactions.getQuantity(),farmerTransactions.getUnits(),farmerTransactions.getEDD(),farmerTransactions.getSNo()});

		
	} 


}

