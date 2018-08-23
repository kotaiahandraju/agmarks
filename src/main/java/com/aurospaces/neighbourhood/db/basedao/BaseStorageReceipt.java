package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.StorageReceipt;
import com.aurospaces.neighbourhood.bean.SupplierReceipt;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

public class BaseStorageReceipt {
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO receipts( Date_in,Name,Mobile,Chamber,Cs_no,Bond_no,Commodity,Variety,Price,Receipts,Insurance,Loan_amt,Banks,Bags_count,Units,Status,Token_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final StorageReceipt storageReceipt) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(storageReceipt.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
ps.setDate(1, java.sql.Date.valueOf((storageReceipt.getStrDateIn())));
ps.setString(2,storageReceipt.getName());
ps.setString(3, storageReceipt.getMobile());
ps.setString(4,storageReceipt.getChamber());
ps.setString(5, storageReceipt.getCsNo());
ps.setString(6,storageReceipt.getBondNo());
ps.setString(7,storageReceipt.getCommodity());
ps.setString(8, storageReceipt.getVariety());
ps.setString(9, storageReceipt.getPrice());
ps.setString(10, storageReceipt.getReceipts());
ps.setString(11, storageReceipt.getInsurance());
ps.setString(12, storageReceipt.getLoanAmt());
ps.setString(13, storageReceipt.getBanks());
ps.setString(14, storageReceipt.getBagsCount());
ps.setString(15, storageReceipt.getUnits());
ps.setString(16, storageReceipt.getStatus());
ps.setString(17, storageReceipt.getTokenId());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				storageReceipt.setSNo(unId.intValue());
				


		}
		else
		{

			String sql = "UPDATE receipts set Date_in =  ?  ,Name =  ?  ,Mobile =  ?  ,Chamber =  ?  ,Cs_no =  ?  ,Bond_no =  ?  ,Commodity =  ?  ,Variety =  ?  ,Price =  ?  ,Receipts =  ?  ,Insurance =  ?  ,Loan_amt =  ?  ,Banks =  ?  ,Bags_count =  ?  ,Units =  ?  ,Status =  ?  ,Token_id = ? where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{storageReceipt.getDateIn() ,storageReceipt.getName(),storageReceipt.getMobile(), storageReceipt.getChamber(),storageReceipt.getCsNo(),storageReceipt.getBondNo(),storageReceipt.getCommodity(),storageReceipt.getVariety(),storageReceipt.getPrice(),storageReceipt.getReceipts(),storageReceipt.getInsurance(),storageReceipt.getLoanAmt(),storageReceipt.getBanks(),storageReceipt.getBagsCount(),storageReceipt.getUnits(),storageReceipt.getStatus(),storageReceipt.getTokenId(), storageReceipt.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM  receipts WHERE SNo=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public StorageReceipt getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from receipts where SNo = ? ";
			List<StorageReceipt> retlist = jdbcTemplate.query(sql,new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(StorageReceipt.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

}
