
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
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseFarmerTransactionsDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO farmer_transactions( Crop_name, Token_id, Category, Input, Variety, Quantity, Units, Area, Area_Units, Comment, Ask_price, Transaction_type, Date, Status,EDD) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final FarmerTransactions farmerTransactions) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(farmerTransactions.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(farmerTransactions.getDate() == null)
					{
					farmerTransactions.setDate( new Date());
					}
					java.sql.Timestamp Date = 
						new java.sql.Timestamp(farmerTransactions.getDate().getTime()); 
					
					if(farmerTransactions.getEDD() == null)
					{
					farmerTransactions.setEDD( new Date());
					}
					java.sql.Timestamp EDD = 
						new java.sql.Timestamp(farmerTransactions.getEDD().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, farmerTransactions.getCropName());
ps.setString(2, farmerTransactions.getTokenId());
ps.setString(3, farmerTransactions.getCategory());
ps.setString(4, farmerTransactions.getInput());
ps.setString(5, farmerTransactions.getVariety());
ps.setString(6, farmerTransactions.getQuantity());
ps.setString(7, farmerTransactions.getUnits());
ps.setString(8, farmerTransactions.getArea());
ps.setString(9, farmerTransactions.getAreaUnits());
ps.setString(10, farmerTransactions.getComment());
ps.setString(11, farmerTransactions.getAskPrice());
ps.setString(12, farmerTransactions.getTransactionType());
ps.setTimestamp(13, Date);
ps.setString(14, farmerTransactions.getStatus());
ps.setTimestamp(15, EDD);

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				farmerTransactions.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE farmer_transactions  set Crop_name = ? ,Token_id = ? ,Category = ? ,Input = ? ,Variety = ? ,Quantity = ? ,Units = ? ,Area = ? ,Area_Units = ? ,Comment = ? ,Ask_price = ? ,Transaction_type = ? ,Status = ?  where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{farmerTransactions.getCropName(),farmerTransactions.getTokenId(),farmerTransactions.getCategory(),farmerTransactions.getInput(),farmerTransactions.getVariety(),farmerTransactions.getQuantity(),farmerTransactions.getUnits(),farmerTransactions.getArea(),farmerTransactions.getAreaUnits(),farmerTransactions.getComment(),farmerTransactions.getAskPrice(),farmerTransactions.getTransactionType(),farmerTransactions.getStatus(),farmerTransactions.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM farmer_transactions WHERE S_no=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public FarmerTransactions getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from farmer_transactions where S_no = ? ";
			List<FarmerTransactions> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(FarmerTransactions.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
