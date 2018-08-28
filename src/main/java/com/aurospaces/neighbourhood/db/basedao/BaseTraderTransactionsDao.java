
package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.FarmerTransactions;
import com.aurospaces.neighbourhood.bean.TraderTransactions;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseTraderTransactionsDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO trader_transactions( Crop_name, Token_id,  Variety, Quantity, Units,  Comment, Ask_price, Transaction_type,EDD, Date, Status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final TraderTransactions traderTransactions) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(traderTransactions.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(traderTransactions.getDate() == null)
					{
						traderTransactions.setDate( new Date());
					}
					java.sql.Timestamp Date = 
						new java.sql.Timestamp(traderTransactions.getDate().getTime()); 
					
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, traderTransactions.getCropName());
ps.setString(2, traderTransactions.getTokenId());
ps.setString(3, traderTransactions.getVariety());
ps.setString(4, traderTransactions.getQuantity());
ps.setString(5, traderTransactions.getUnits());
ps.setString(6, traderTransactions.getComment());
ps.setString(7, traderTransactions.getAskPrice());
ps.setString(8, traderTransactions.getTransactionType());
ps.setString(9, traderTransactions.getEDD());
ps.setTimestamp(10, Date);
ps.setString(11, traderTransactions.getStatus());


							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				traderTransactions.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE trader_transactions  set Crop_name = ? ,Token_id = ? ,Variety = ? ,Quantity = ? ,Units = ? ,Comment = ? ,Ask_price = ? ,Transaction_type = ? ,Status = ?  where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{traderTransactions.getCropName(),traderTransactions.getTokenId(),traderTransactions.getVariety(),traderTransactions.getQuantity(),traderTransactions.getUnits(),traderTransactions.getComment(),traderTransactions.getAskPrice(),traderTransactions.getTransactionType(),traderTransactions.getStatus(),traderTransactions.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM trader_transactions WHERE S_no=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public FarmerTransactions getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from trader_transactions where S_no = ? ";
			List<FarmerTransactions> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(FarmerTransactions.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
