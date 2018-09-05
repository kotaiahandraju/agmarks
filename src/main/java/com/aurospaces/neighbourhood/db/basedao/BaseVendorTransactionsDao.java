
package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.VendorTransactions;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseVendorTransactionsDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO vendor_transactions( Crop_name, Token_id,  Variety, Quantity, Units,  Comment, Ask_price, Transaction_type,EDD, Date, Status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final VendorTransactions vendorTransactions) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(vendorTransactions.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(vendorTransactions.getDate() == null)
					{
						vendorTransactions.setDate( new Date());
					}
					java.sql.Timestamp Date = 
						new java.sql.Timestamp(vendorTransactions.getDate().getTime()); 
					
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, vendorTransactions.getCropName());
ps.setString(2, vendorTransactions.getTokenId());
ps.setString(3, vendorTransactions.getVariety());
ps.setString(4, vendorTransactions.getQuantity());
ps.setString(5, vendorTransactions.getUnits());
ps.setString(6, vendorTransactions.getComment());
ps.setString(7, vendorTransactions.getAskPrice());
ps.setString(8, vendorTransactions.getTransactionType());
ps.setString(9, vendorTransactions.getEDD());
ps.setTimestamp(10, Date);
ps.setString(11, vendorTransactions.getStatus());


							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				vendorTransactions.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE vendor_transactions  set Crop_name = ? ,Token_id = ? ,Variety = ? ,Quantity = ? ,Units = ? ,Comment = ? ,Ask_price = ? ,Transaction_type = ? ,Status = ?  where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{vendorTransactions.getCropName(),vendorTransactions.getTokenId(),vendorTransactions.getVariety(),vendorTransactions.getQuantity(),vendorTransactions.getUnits(),vendorTransactions.getComment(),vendorTransactions.getAskPrice(),vendorTransactions.getTransactionType(),vendorTransactions.getStatus(),vendorTransactions.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM vendor_transactions WHERE S_no=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 
	

}
