
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

import com.aurospaces.neighbourhood.bean.TradertdaTrans;
import com.aurospaces.neighbourhood.bean.VendorsvdaTrans;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseTdaTransDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;


	public final String INSERT_SQL = "INSERT INTO tda_trans( Token_id, Live_stock, Inputs, Variety, Quantity, Unit, Milk_yield, Price, Nearest_market, Comment, Transaction_type, Status, Date,EDD) values (?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final TradertdaTrans tradertdaTrans) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(tradertdaTrans.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(tradertdaTrans.getDate() == null)
					{
					tradertdaTrans.setDate( new Date());
					}
					java.sql.Timestamp Date = 
						new java.sql.Timestamp(tradertdaTrans.getDate().getTime()); 
						
					
					
					
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, tradertdaTrans.getTokenId());
ps.setString(2, tradertdaTrans.getLiveStock());
ps.setString(3, tradertdaTrans.getInputs());
ps.setString(4, tradertdaTrans.getVariety());
ps.setString(5, tradertdaTrans.getQuantity());
ps.setString(6, tradertdaTrans.getUnit());
ps.setString(7, tradertdaTrans.getMilkYield());
ps.setInt(8, tradertdaTrans.getPrice());
ps.setString(9, tradertdaTrans.getNearestMarket());
ps.setString(10, tradertdaTrans.getComment());
ps.setString(11, tradertdaTrans.getTransactionType());
ps.setString(12, tradertdaTrans.getStatus());
ps.setTimestamp(13, Date);
ps.setString(14, tradertdaTrans.getEDD());


							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				tradertdaTrans.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE tda_trans  set Token_id = ? ,Live_stock = ? ,Inputs = ? ,Variety = ? ,Quantity = ? ,Unit = ? ,Milk_yield = ? ,Price = ? ,Nearest_market = ? ,Comment = ? ,Transaction_type = ? ,Status = ?  where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{tradertdaTrans.getTokenId(),tradertdaTrans.getLiveStock(),tradertdaTrans.getInputs(),tradertdaTrans.getVariety(),tradertdaTrans.getQuantity(),tradertdaTrans.getUnit(),tradertdaTrans.getMilkYield(),tradertdaTrans.getPrice(),tradertdaTrans.getNearestMarket(),tradertdaTrans.getComment(),tradertdaTrans.getTransactionType(),tradertdaTrans.getStatus(),tradertdaTrans.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM tda_trans WHERE S_no=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public TradertdaTrans getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from tda_trans where S_no = ? ";
			List<TradertdaTrans> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(TradertdaTrans.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

}
