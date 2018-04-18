
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

import com.aurospaces.neighbourhood.bean.CommPrices;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseCommPricesDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO comm_prices( State, District, Market, Commodity, Variety, Units, Min_price, Max_price, Modal_price, Unit_of_price, Date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final CommPrices commPrices) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(commPrices.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(commPrices.getDate() == null)
					{
					commPrices.setDate( new Date());
					}
					java.sql.Timestamp Date = 
						new java.sql.Timestamp(commPrices.getDate().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"Id"});
	ps.setString(1, commPrices.getState());
ps.setString(2, commPrices.getDistrict());
ps.setString(3, commPrices.getMarket());
ps.setString(4, commPrices.getCommodity());
ps.setString(5, commPrices.getVariety());
ps.setString(6, commPrices.getUnits());
ps.setInt(7, commPrices.getMinPrice());
ps.setInt(8, commPrices.getMaxPrice());
ps.setInt(9, commPrices.getModalPrice());
ps.setString(10, commPrices.getUnitOfPrice());
ps.setTimestamp(11, Date);

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				commPrices.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE comm_prices  set State = ? ,District = ? ,Market = ? ,Commodity = ? ,Variety = ? ,Units = ? ,Min_price = ? ,Max_price = ? ,Modal_price = ? ,Unit_of_price = ? ,Date = ?  where Id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{commPrices.getState(),commPrices.getDistrict(),commPrices.getMarket(),commPrices.getCommodity(),commPrices.getVariety(),commPrices.getUnits(),commPrices.getMinPrice(),commPrices.getMaxPrice(),commPrices.getModalPrice(),commPrices.getUnitOfPrice(),commPrices.getDate(),commPrices.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM comm_prices WHERE Id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public CommPrices getById(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from comm_prices where Id = ? ";
			List<CommPrices> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(CommPrices.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
