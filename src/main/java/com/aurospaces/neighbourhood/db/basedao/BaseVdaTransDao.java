
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


public class BaseVdaTransDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO vda_trans( Token_id, Live_stock, Inputs, Variety, Quantity, Unit, Milk_yield, Price, Nearest_market, Comment, Transaction_type, Status, Date,EDD) values (?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final VendorsvdaTrans vendorsvdaTrans) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(vendorsvdaTrans.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(vendorsvdaTrans.getDate() == null)
					{
					vendorsvdaTrans.setDate( new Date());
					}
					java.sql.Timestamp Date = 
						new java.sql.Timestamp(vendorsvdaTrans.getDate().getTime()); 
						
					
					
					
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, vendorsvdaTrans.getTokenId());
ps.setString(2, vendorsvdaTrans.getLiveStock());
ps.setString(3, vendorsvdaTrans.getInputs());
ps.setString(4, vendorsvdaTrans.getVariety());
ps.setString(5, vendorsvdaTrans.getQuantity());
ps.setString(6, vendorsvdaTrans.getUnit());
ps.setString(7, vendorsvdaTrans.getMilkYield());
ps.setInt(8, vendorsvdaTrans.getPrice());
ps.setString(9, vendorsvdaTrans.getNearestMarket());
ps.setString(10, vendorsvdaTrans.getComment());
ps.setString(11, vendorsvdaTrans.getTransactionType());
ps.setString(12, vendorsvdaTrans.getStatus());
ps.setTimestamp(13, Date);
ps.setString(14, vendorsvdaTrans.getEDD());


							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				vendorsvdaTrans.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE vda_trans  set Token_id = ? ,Live_stock = ? ,Inputs = ? ,Variety = ? ,Quantity = ? ,Unit = ? ,Milk_yield = ? ,Price = ? ,Nearest_market = ? ,Comment = ? ,Transaction_type = ? ,Status = ?  where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{vendorsvdaTrans.getTokenId(),vendorsvdaTrans.getLiveStock(),vendorsvdaTrans.getInputs(),vendorsvdaTrans.getVariety(),vendorsvdaTrans.getQuantity(),vendorsvdaTrans.getUnit(),vendorsvdaTrans.getMilkYield(),vendorsvdaTrans.getPrice(),vendorsvdaTrans.getNearestMarket(),vendorsvdaTrans.getComment(),vendorsvdaTrans.getTransactionType(),vendorsvdaTrans.getStatus(),vendorsvdaTrans.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM vda_trans WHERE S_no=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public VendorsvdaTrans getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from vda_trans where S_no = ? ";
			List<VendorsvdaTrans> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(VendorsvdaTrans.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
