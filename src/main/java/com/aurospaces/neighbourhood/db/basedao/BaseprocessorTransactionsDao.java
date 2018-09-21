
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
import com.aurospaces.neighbourhood.bean.ProcessorTransactions;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseprocessorTransactionsDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO proc_transactions( Name,  Variety, Quantity, Units,   Ask_price, Comment, T_status,EDD, Token_id, Status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final ProcessorTransactions processorTransactions) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(processorTransactions.getSno() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(processorTransactions.getDate() == null)
					{
						processorTransactions.setDate( new Date());
					}
					java.sql.Timestamp Date = 
						new java.sql.Timestamp(processorTransactions.getDate().getTime()); 
					
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, processorTransactions.getName());
ps.setString(2, processorTransactions.getVariety());
ps.setInt(3, processorTransactions.getQuantity());
ps.setString(4, processorTransactions.getUnits());
ps.setString(5, processorTransactions.getAskPrice());
ps.setString(6, processorTransactions.getComment());
ps.setString(7, processorTransactions.getEDD());
ps.setString(8, processorTransactions.getStatus());
ps.setString(9, processorTransactions.getTokenId());


							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				processorTransactions.setSno(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE proc_transactions  set Crop_name = ? ,Token_id = ? ,Variety = ? ,Quantity = ? ,Units = ? ,Comment = ? ,Ask_price = ? ,Transaction_type = ? ,Status = ?  where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{processorTransactions.getName(),processorTransactions.getTokenId(),processorTransactions.getVariety(),processorTransactions.getQuantity(),processorTransactions.getUnits(),processorTransactions.getComment(),processorTransactions.getAskPrice(),processorTransactions.getTStatus(),processorTransactions.getStatus(),processorTransactions.getSno()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM proc_transactions WHERE S_no=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public ProcessorTransactions getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from proc_transactions where S_no = ? ";
			List<ProcessorTransactions> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(ProcessorTransactions.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
