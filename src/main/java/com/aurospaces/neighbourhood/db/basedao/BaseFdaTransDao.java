
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

import com.aurospaces.neighbourhood.bean.FdaTrans;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseFdaTransDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO fda_trans( Token_id, Live_stock, Inputs, Variety, Quantity, Unit, Milk_yield, Price, Nearest_market, Comment, Transaction_type, Status, Date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final FdaTrans fdaTrans) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(fdaTrans.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(fdaTrans.getDate() == null)
					{
					fdaTrans.setDate( new Date());
					}
					java.sql.Timestamp Date = 
						new java.sql.Timestamp(fdaTrans.getDate().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, fdaTrans.getTokenId());
ps.setString(2, fdaTrans.getLiveStock());
ps.setString(3, fdaTrans.getInputs());
ps.setString(4, fdaTrans.getVariety());
ps.setString(5, fdaTrans.getQuantity());
ps.setString(6, fdaTrans.getUnit());
ps.setString(7, fdaTrans.getMilkYield());
ps.setInt(8, fdaTrans.getPrice());
ps.setString(9, fdaTrans.getNearestMarket());
ps.setString(10, fdaTrans.getComment());
ps.setString(11, fdaTrans.getTransactionType());
ps.setString(12, fdaTrans.getStatus());
ps.setTimestamp(13, Date);

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				fdaTrans.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE fda_trans  set Token_id = ? ,Live_stock = ? ,Inputs = ? ,Variety = ? ,Quantity = ? ,Unit = ? ,Milk_yield = ? ,Price = ? ,Nearest_market = ? ,Comment = ? ,Transaction_type = ? ,Status = ?  where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{fdaTrans.getTokenId(),fdaTrans.getLiveStock(),fdaTrans.getInputs(),fdaTrans.getVariety(),fdaTrans.getQuantity(),fdaTrans.getUnit(),fdaTrans.getMilkYield(),fdaTrans.getPrice(),fdaTrans.getNearestMarket(),fdaTrans.getComment(),fdaTrans.getTransactionType(),fdaTrans.getStatus(),fdaTrans.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM fda_trans WHERE S_no=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public FdaTrans getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from fda_trans where S_no = ? ";
			List<FdaTrans> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(FdaTrans.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
